import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

public class Field implements ActionListener {
	static JButton button[] = new JButton[9];
	private static String figurePath = "D:/java/pics/";
	JFrame frame;

	Field() {
		setButtons();
	}

	void setFrame(JFrame frm) {
		frame = frm;
		frame.setLayout(new FlowLayout());
		for (int a = 0; a < button.length; a++)
			frame.add(button[a]);
	}

	static void refresh() {
		for (JButton btn : button)
			btn.setIcon(new ImageIcon("D:/java/pics/empty.png"));

	}

	void setButtons() {
		for (int a = 0; a < button.length; a++) {
			button[a] = new JButton();
			button[a].setActionCommand("" + a);
			button[a].setBorderPainted(false);
			button[a].setContentAreaFilled(false);
			button[a].setFocusPainted(false);
			button[a].setBorder(new EmptyBorder(0, 0, 0, 0));
			button[a].addActionListener(this);
			button[a].setIcon(new ImageIcon("D:/java/pics/empty.png"));
		}
	}

	void placeFigure(int index, String figure) {
		if (figure != null) {
			String tmp = figurePath + figure + ".png";
			System.out.println(tmp);
			button[index].setIcon(new ImageIcon(tmp));
		}
		// button[index].setEnabled(false);
	}

	public void actionPerformed(ActionEvent ae) {
		int index = Integer.parseInt(ae.getActionCommand());
		boolean check;
		do {
			check = Interaction.sendMove(index);
		} while (!check);
		placeFigure(index, Interaction.getFigure());
		// button[a].removeActionListener(this);
	}
}
