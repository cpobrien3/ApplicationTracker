import javax.swing.JFrame;

import view.MainMenuJPanel;

public class StartProgram {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		//Menu to start the program
		JFrame frame = new JFrame();
		MainMenuJPanel panel = new MainMenuJPanel();
		frame.add(panel);

		frame.setSize(500, 320);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}
