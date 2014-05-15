import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;

import javax.swing.*;

public class LogIn implements ActionListener {
	private static JFrame frame;
	private static JPanel panel;
	private static JTextArea log;
	private static JTextField host;
	private static JTextField port;
	private static JScrollPane scrl;
	private static Connection adjust;
	private static Field field;

	LogIn() {

	}

	LogIn(JFrame frame) {
		this.frame = frame;
	}

	void adjustAppearance() {
		panel = new JPanel(new BorderLayout());
		log = new JTextArea(16, 17);
		log.setEditable(false);
		log.setFocusable(false);
		log.setBackground(new Color(96, 96, 96));
		scrl = new JScrollPane(log);
		scrl.setPreferredSize(new Dimension(10, 225));
		host = new JTextField(16);
		host.setText("127.0.0.1");
		port = new JTextField(5);
		port.addActionListener(this);
		port.setText("8189");
		setLogText("Input IP and PORT and press enter.");
		panel.add(host, BorderLayout.LINE_START);
		panel.add(port, BorderLayout.LINE_END);
		frame.add(panel, BorderLayout.PAGE_END);
		frame.add(scrl, BorderLayout.PAGE_START);
	}

	static void hideAll() {
		panel.setVisible(false);
		scrl.setVisible(false);
		log.setText("");
	}

	static void goToField() {
		hideAll();
		field = new Field(frame);
		Field.setFigure(Connection.getFigure());
	}

	static void clearField() {
		field.clearUp();
	}

	static void shutDown() {
		setLogText("Server closed\nRestart programm");
		field.hideAll();
		panel.setVisible(true);
		port.setEditable(false);
		host.setEditable(false);
		scrl.setVisible(true);
	}

	static void setLogText(String msg) {
		log.append(msg + "\n");
	}

	static void putFigure(int index) {
		field.putFigure(index);
	}

	static boolean canMove() {
		return Connection.sleeps();
	}

	public void actionPerformed(ActionEvent ae) {
		if (adjust != null && Connection.isConnected()) {
			LogIn.goToField();
			Connection initialize = new Connection();
		} else {
			String hostArg = host.getText();
			try {
				int portArg = Integer.parseInt(port.getText());
				adjust = new Connection(hostArg, portArg);
			} catch (NumberFormatException exc) {
				setLogText("Bad input!");
			}
		}
	}
}
