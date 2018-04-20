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

/**
 * @author Timo Freitag
 *
 */
public class Server {
	private int port;
	private Map<String, BooleanSupplier> functions = new HashMap<String, BooleanSupplier>();
	private JavaCPNInterface cpn = new JavaCPN();
	
	public Server(int port) {
		this.port = port;
	}
	
	public void communicate() throws IOException {
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
				//boolean result = functions.get(msg).getAsBoolean();
				//cpn.send(EncodeDecode.encode(Boolean.toString(result)));
				System.out.println(msg);
				boolean flag = false;
				if(msg.equals("find"))
					cpn.send(EncodeDecode.encode(Boolean.toString(flag)));
				else
					cpn.send(EncodeDecode.encode(Boolean.toString(true)));
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
