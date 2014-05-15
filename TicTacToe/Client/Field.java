import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Field implements ActionListener {
	private JFrame frame;
	private JPanel panel;
	private static char myFigure;
	private static char seFigure;
	JButton button[] = new JButton[9];

	Field() {

	}

	Field(JFrame frame) {

		this.frame = frame;
		setField();
	}

	static void setFigure(char figure) {
		if (figure == 'o') {
			seFigure = 'x';
		} else {
			seFigure = 'o';
		}
		myFigure = figure;
	}

	void clearUp() { // Possibly working
		for (int a = 0; a < button.length; a++)
			button[a].setIcon(new ImageIcon("D:/java/pics/empty.png"));
	}

	void putFigure(int index) {
		button[index]
				.setIcon(new ImageIcon("D:/java/pics/" + seFigure + ".png"));
	}

	void setField() {
		setButtons();
		panel = new JPanel(new FlowLayout());
		for (int a = 0; a < button.length; a++) {
			panel.add(button[a]);
		}
		panel.setBackground(new Color(76, 76, 76));
		frame.add(panel);
	}

	void hideAll() {
		panel.setVisible(false);
	}

	void showAll() {
		panel.setVisible(true);
	}

	private void setButtons() {
		for (int a = 0; a < button.length; a++) {
			button[a] = new JButton();
			button[a].setBorderPainted(false);
			button[a].setContentAreaFilled(false);
			button[a].setFocusPainted(false);
			button[a].setBorder(new EmptyBorder(0, 0, 0, 0));
			button[a].addActionListener(this);
			button[a].setIcon(new ImageIcon("D:/java/pics/empty.png"));
			button[a].setActionCommand("" + a);
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		int index = Integer.parseInt(ae.getActionCommand());
			button[index].setIcon(new ImageIcon("D:/java/pics/" + myFigure
					+ ".png"));
			Connection.writeServer(index + "");
	}
}
