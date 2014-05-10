import java.io.*;
import java.net.*;
import java.util.*;

public class Interaction implements Runnable {
	private static String figure;
	private Socket socket;
	private String host;
	private int port;
	private static Scanner in;
	static private PrintWriter out;

	Interaction(String host, int port) {
		this.host = host;
		this.port = port;
		Thread selfLinked = new Thread(this);
		selfLinked.start();
	}

	public void run() {
		try {
			MainFrame.setLogMsg("Trying to connect...");
			socket = new Socket(host, port);
			in = new Scanner(socket.getInputStream());
			out = new PrintWriter(socket.getOutputStream(), true);
			MainFrame.setLogMsg("Successfully connected!");
			figure = in.nextLine();
			MainFrame.hidePanel();
			MainFrame.createField();
		} catch (ConnectException e) {
			MainFrame.setLogMsg("Failed to connect!");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (socket.isConnected()) {
			readServer();
		}
	}

	static boolean sendMove(int index) {
		out.println(index);
		if (readServer().equals("t"))
			return true;
		if (readServer().equals("r"))
			Field.refresh();
		return false;
	}

	static String getFigure() {
		return figure;
	}

	static String readServer() {
		String msg = "";
		if (in.hasNextLine()) {
			msg += in.nextLine();
			System.out.println(msg);
		}
		return msg;
	}

}
