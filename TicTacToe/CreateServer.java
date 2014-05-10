import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

class Server implements Runnable {
	private Socket s;
	private char myFigure;
	Scanner in;
	PrintWriter out;

	Server(Socket socket, char figure) {
		s = socket;
		myFigure = figure;
	}

	public void run() {
		try {
			InputStream inStream = s.getInputStream();
			in = new Scanner(inStream);
			out = new PrintWriter(s.getOutputStream(), true);
			out.println(myFigure);
			String tmp = "";
			while (in.hasNextLine()) {
				if (CreateServer.checkIfFull())
					CreateServer.notify(null, "r");
				tmp = in.nextLine();
				if (tmp.equals("stop!")) {
					CreateServer.closeServer();
				}
				int temp = Integer.parseInt(tmp);
				if (CreateServer.checkSpot(temp)) {
					out.println("t");
					CreateServer.notify(s, tmp);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

public class CreateServer {
	private static int port = 8189;
	private static ServerSocket s;
	static boolean field[] = new boolean[9];
	private static List<Socket> userList = new ArrayList<Socket>();

	static void notify(Socket except, String msg) throws IOException {
		for (Socket get : userList)
			if (except != get) {
				PrintWriter stream = new PrintWriter(get.getOutputStream(),
						true);
				stream.println("-" + msg);

			}
	}

	static void blockOut(Socket mySocket) {
		mySocket.
	}

	static boolean checkIfFull() {
		for (int a = 0; a < field.length; a++)
			if (!field[a])
				return false;
		for (boolean b : field)
			b = false;
		return true;
	}

	static void closeServer() {
		try {
			for (Socket tmp : userList)
				tmp.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void addUser(Socket usr) {
		userList.add(usr);
	}

	static boolean checkSpot(int index) {
		if (field[index])
			return false;
		field[index] = true;
		return true;
	}

	public static void main(String[] args) {
		int counter = 1;
		char figure = 0;
		try {
			s = new ServerSocket(port);
			while (true) {
				Socket user = s.accept();
				addUser(user);
				if (counter % 2 == 0)
					figure = 'o';
				else
					figure = 'x';
				Runnable r = new Server(user, figure);
				Thread t = new Thread(r);
				t.start();
				counter++;
			}
		} catch (IOException exc) {
			exc.printStackTrace();
		}

	}

}
