import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class Connection implements Runnable {
	private String host;
	private int port;
	private static Socket socket;
	private static Scanner in;
	private static PrintWriter out;
	private Thread thread;
	private static boolean sleep;
	private static String fig;

	Connection() {
		thread = new Thread(this);
		thread.start();
	}

	static boolean isConnected() {
		return socket.isConnected();
	}

	static boolean sleeps() {
		return sleep;
	}

	static char getFigure() {
		return fig.charAt(0);
	}

	Connection(final String host, final int port) {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					LogIn.setLogText("Connecting" + host + ":" + port);
					socket = new Socket(host, port);
					in = new Scanner(socket.getInputStream());
					out = new PrintWriter(socket.getOutputStream(), true);
					String tmp = in.nextLine();
					System.out.println(tmp);
					if (tmp.equals("o")) {
						sleep = true;
						fig = "o";
					} else{
						sleep=false;
						fig = "x";
					}
					LogIn.setLogText("Successfully connected");
					LogIn.setLogText("Press enter to play");
				} catch (IOException e) {
					LogIn.setLogText("Failed to connect");
					e.printStackTrace();
				}
			}
		});
		System.out.println("1");
		thread.start();
		this.host = host;
		this.port = port;
	}

	String readServer() {
		String arg = null;
		if (in.hasNextLine())
			arg = in.nextLine();
		return arg;
	}

	static void writeServer(String msg) {
		out.println(msg);
	}

	public void run() {
		do {
			String msg = readServer();
			System.out.println(msg);
			if (msg.charAt(0) == '-')
				LogIn.putFigure(msg.charAt(1) - 48);
			if (msg != null) { //break-up point has to be added 
				if (msg.equals("r"))
					LogIn.clearField();
				if (msg.equals("t"))
					sleep = true;
				if (msg.equals("closing"))
					try {
						LogIn.shutDown();
						socket.close();
						thread.stop();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
		} while (socket.isConnected());
	}
}
