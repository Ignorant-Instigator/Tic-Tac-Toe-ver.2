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
	private JTextField host, port;
	private static JScrollPane scrl;

	LogIn() {

	}

	LogIn(JFrame frame) {
		this.frame = frame;
	}

	void adjustAppearance() {
		panel = new JPanel(new BorderLayout());
		log = new JTextArea(16, 17);
		log.setEditable(false);
		log.setBackground(new Color(96, 96, 96));
		scrl = new JScrollPane(log);
		scrl.setPreferredSize(new Dimension(10, 250));
		host = new JTextField(20);
		host.setText("127.0.0.1");
		port = new JTextField(6);
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
		Field field = new Field(frame);
	}

	private void showAll() {
		panel.setVisible(true);
		scrl.setVisible(true);
	}

	static void setLogText(String msg) {
		log.append(msg + "\n");
	}

	public void actionPerformed(ActionEvent ae) {
		String hostArg = host.getText();
		Connection adjust = null;
		try {
			int portArg = Integer.parseInt(port.getText());
			adjust = new Connection(hostArg, portArg);
		} catch (NumberFormatException exc) {
			setLogText("Bad input!");
		}
	}
}
