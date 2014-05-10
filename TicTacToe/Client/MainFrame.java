import java.awt.*;
import javax.swing.*;

public class MainFrame {
	LogIn log ;
	JFrame frame;

	MainFrame() {
		setFrame();
		log = new LogIn(frame);
		log.adjustAppearance();
	}

	void setFrame() {
		frame = new JFrame("Client");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame();
			}
		});

	}

}
