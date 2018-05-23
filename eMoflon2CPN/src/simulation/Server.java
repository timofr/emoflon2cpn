/**
 * 
 */
package simulation;

import java.io.IOException;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

import javacpn.EncodeDecode;
import javacpn.JavaCPN;
import javacpn.JavaCPNInterface;
import translation.Translation;

/**
 * @author Timo Freitag
 *
 */
public class Server {
	private int port;
	private Map<String, BooleanSupplier> functions = new HashMap<String, BooleanSupplier>();
	private JavaCPNInterface cpn = new JavaCPN();
	private String path;
	private Simulation simulation = new Simulation();
	private Consumer<String> writer;
	
	public Server(int port, Consumer<String> writer) {
		this.writer = writer;
		this.port = port;
	}
	
	public void communicate() {
		simulation.initialize();
		try {
			cpn.connect("localhost", port);
			polling();
			cpn.disconnect();
		} catch (IOException e) {
			throw new SimulationException(e);
		}
	}
	
	private void polling() {
		boolean finished = false;
		try {
			while(!finished) {
				String msg = EncodeDecode.decodeString(cpn.receive());
				boolean result = false;
				try {
					result = simulation.invoke(msg);
				} catch (SimulationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				writer.accept("ActivityNode: \"" + msg + "\" Result: \"" + result + "\"");
				cpn.send(EncodeDecode.encode(Boolean.toString(result)));
			}
		}
		catch(IOException e) {
			throw new DisconnectedException("CPN Tools disconnected.");
		}
	}
	
	
}
