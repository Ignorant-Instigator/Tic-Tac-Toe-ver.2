import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class Connection implements Runnable {
	private String host;
	private int port;
	private Socket socket;
	private Scanner in;
	private PrintWriter out;

	Connection() {

	}

	boolean isConnected() {
		return socket.isConnected();
	}

	Connection(final String host, final int port) {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					LogIn.setLogText("Connecting" + host + ":" + port);
					socket = new Socket(host, port);
					in = new Scanner(socket.getInputStream());
					out = new PrintWriter(socket.getOutputStream(), true);
					LogIn.setLogText("Successfully connected");
					LogIn.goToField();
				} catch (IOException e) {
					LogIn.setLogText("Failed to connect");
					e.printStackTrace();
				}
			}
		});
		thread.start();
		this.host = host;
		this.port = port;
	}

	public void run() {

	}
}
