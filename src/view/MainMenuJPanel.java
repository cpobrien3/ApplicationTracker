package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.MainMenuJPanel.ButtonListener;

public class MainMenuJPanel extends JPanel{
	
	//Create four buttons
	JButton btnAddNewJob = new JButton("Add New Job");
	JButton btnAddNewApplication = new JButton("Add New Application");
	JButton btnAddNewCandidate = new JButton("Add New Candidate");
	private final JButton btnViewAllApplications = new JButton("View All Job Applications");
	
	/**
	 * Create the panel.
	 */
	public MainMenuJPanel() {
		
		JLabel instructions = new JLabel(" SELECT AN OPTION TO CONTINUE ");
		
		//Add the buttons and a button listener to the panel
		ButtonListener bl = new ButtonListener();
		btnAddNewJob.addActionListener(bl);
		btnAddNewApplication.addActionListener(bl);
		btnAddNewCandidate.addActionListener(bl);
		btnViewAllApplications.addActionListener(bl);
		
		JPanel buttons = new JPanel();
		setLayout(new BorderLayout());
		add(instructions, BorderLayout.NORTH);
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS));
		buttons.add(btnAddNewJob);
		buttons.add(btnAddNewApplication);
		buttons.add(btnAddNewCandidate);
		buttons.add(btnViewAllApplications);
		
		add(buttons, BorderLayout.CENTER);
	}
	
	//ButtonListenerClass
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JPanel newPanel = new JPanel();
			//Add Job button
			if (e.getSource() == btnAddNewJob) {
				newPanel = new AddJobJPanel();	
			} else if (e.getSource() == btnAddNewApplication) { //Add Application button
				newPanel = new AddApplicationJPanel();
			} else if (e.getSource() == btnAddNewCandidate) {  //Add Candidate button
				newPanel = new AddCandidateJPanel();
			} else if (e.getSource() == btnViewAllApplications) { //View Application button
				newPanel = new ViewAllApplicationsJPanel();
			
			}
			sendToNewPanel(newPanel);

		}
		
		/**
		 * Switches the panel
		 * @param newPanel - panel that user clicks on
		 */
		private void sendToNewPanel(JPanel newPanel) {
	
			removeAll();
			setVisible(false);
			add(newPanel);
			validate();
			setVisible(true);

		}
	}			
}
