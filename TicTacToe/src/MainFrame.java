import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MainFrame {
	private static JFrame frame;
	private static JPanel menu = new JPanel(new BorderLayout());
	private JTextField host = new JTextField(15);
	private JTextField port = new JTextField(5);
	private static JScrollPane scrl;
	private static JButton proceed = new JButton("Connect");
	private static JTextArea log = new JTextArea(10, 10);
	private Interaction access;
	private static Field field;

	MainFrame() {
		setUpFrame();
		field = new Field();

	}

	private void setUpFrame() {
		frame = new JFrame("TicTacToe");
		frame.setSize(252, 273);
		frame.setLayout(new GridBagLayout());
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(76, 76, 76));
		host.setText("127.0.0.1");
		port.setText("8189");
		scrl = new JScrollPane(log);
		log.setEditable(false);
		log.setBackground(Color.GRAY);
		menu.add(host, BorderLayout.CENTER);
		menu.add(port, BorderLayout.LINE_END);
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.PAGE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0;
		frame.add(scrl, c);
		c.fill = 0;
		c.gridy = 1;
		frame.add(menu, c);
		c.gridy = 2;
		c.anchor = GridBagConstraints.EAST;
		proceed.setFocusable(false);
		proceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int portNum = Integer.parseInt(port.getText());
				access = new Interaction(host.getText(), portNum);
			}
		});
		frame.add(proceed, c);
		frame.setVisible(true);
	}

	static void createField() {
		field.setFrame(frame);
	}

	static void hidePanel() {
		scrl.setVisible(false);
		menu.setVisible(false);
		proceed.setVisible(false);
	}

	private void showPanel() {
		menu.setVisible(true);
	}

	static void setLogMsg(String msg) {
		log.append(msg + "\n");
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame();
			}
		});

	}

}
