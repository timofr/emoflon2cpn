/**
 * 
 */
package simulation;

import java.io.IOException;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BooleanSupplier;

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
	
	public Server(int port) {
		this.port = port;
	}
	
	public void communicate() throws IOException, ClassNotFoundException, SimulationException {
		simulation.initialize();
		cpn.connect("localhost", port);
		System.out.println("Connected");
		polling();
		cpn.disconnect();
		System.out.println("Disconnected");
	}
	
	public void polling() {
		boolean finished = false;
		try {
			while(!finished) {
				String msg = EncodeDecode.decodeString(cpn.receive());
				System.out.println(msg);
				boolean result = false;
				try {
					result = simulation.invoke(msg);
				} catch (SimulationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cpn.send(EncodeDecode.encode(Boolean.toString(result)));
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
