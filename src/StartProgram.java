import javax.swing.JFrame;

import view.MainMenuJPanel;

/**************************************************************
* Name        : Final Project
* Author      : Christian O'Brien
* Created     : 12/08/2021
* Course      : CIS 152 Data Structures
* Version     : 1.0
* OS          : Windows 10
* Copyright   : This is my own original work based on
*               specifications issued by our instructor
* Description : Application Tracker program that lets a business owner add job openings and view a list of applicants.
*               Input: Jobs, Applications, and Candidates
*               Output: Each job and application in a sorted linked list and a priority queue of candidates.
* Academic Honesty: I attest that this is my original work.
* I have not used unauthorized source code, either modified or 
* unmodified. I have not given other fellow student(s) access to
* my program.         
***************************************************************/

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
