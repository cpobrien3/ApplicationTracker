package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import controller.ApplicationFileHelper;
import controller.CandidateFileHelper;
import controller.FileHelper;
import controller.JobFileHelper;
import model.Application;
import model.Candidate;
import model.Job;

public class AddApplicationJPanel extends JPanel {

	//Create the text fields and combo boxes needed for the Jpanel
	private JTextField textYearsEducation;
	private JTextField textFieldYearsExp;
	private JComboBox<?> canidateTextField;
	private JComboBox<?> jobTextField;
	private JButton btnSubmitNewApplicant;
	private JButton btnClear;
	private JButton backToMain;
	
	//Create 3 file helpers
	ApplicationFileHelper applicationFileHelper = new ApplicationFileHelper();
	CandidateFileHelper canidateFileHelper = new CandidateFileHelper();
	JobFileHelper jobFileHelper = new JobFileHelper();

	//Create to three Linked List to read/write files
	LinkedList<Application> allApplications;
	LinkedList<Candidate> allCanidates;
	LinkedList<Job> allJobs;

	
	/**
	 * Panel that adds Application to the file
	 */
	public AddApplicationJPanel() {
		
		//Only go to the page if candidates and jobs files exist
		if (!(canidateFileHelper.doesAFileExist())) {
			String message = "No Candidates to Use.  Please add an Canidate first";
			popUpErrorMessage(message);
		} else if (!(jobFileHelper.doesAFileExist())) {
			String message = "No Jobs to Use.  Please add a job first";
			popUpErrorMessage(message);
		} else {
			
			//Set the layout of the form
			setLayout(new BorderLayout());
			JLabel title = new JLabel("ENTER A NEW APPLICATION:");
			add(title, BorderLayout.NORTH);

			allJobs = (LinkedList<Job>) jobFileHelper.readFile();
			allCanidates = (LinkedList<Candidate>) canidateFileHelper.readFile();
			allApplications = (LinkedList<Application>) applicationFileHelper.readFile();

			JPanel form = new JPanel();
			form.setLayout(new FormLayout(
					new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
							FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
							FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, },
					new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
							FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
							FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
							FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
							FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
							FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

			//Input for Years Experience
			JLabel lblExp = new JLabel("Years experience:");
			form.add(lblExp, "2, 6, right, default");
			textFieldYearsExp = new JTextField();
			form.add(textFieldYearsExp, "4, 6, left, default");
			textFieldYearsExp.setColumns(5);

			//Input for Years Education
			JLabel lblYears = new JLabel("Years Higher Education:");
			form.add(lblYears, "2, 8, right, default");
			textYearsEducation = new JTextField();
			form.add(textYearsEducation, "4, 8, left, default");
			textYearsEducation.setColumns(5);

			//Input for which job to apply to
			LinkedList<String> allJobsList = formatJobsListToString(allJobs);
			JLabel lblJob = new JLabel("Job:");
			form.add(lblJob, "2, 12, right, default");
			jobTextField = new JComboBox(allJobsList.toArray());
			form.add(jobTextField, "4, 12, 5, 1, fill, default");

			//Input for what candidate to apply to
			PriorityQueue<String> allCanidatesList = formatCanidatesListToString(allCanidates);
			JLabel lblCanidate = new JLabel("Canidate:");
			form.add(lblCanidate, "2, 14, right, default");
			canidateTextField = new JComboBox(allCanidatesList.toArray());
			form.add(canidateTextField, "4, 14, 5, 1, fill, default");

			//Buttons to submit, clear, and go back to homepage
			btnSubmitNewApplicant = new JButton("Add new Application");
			buttonListener bl = new buttonListener();
			btnSubmitNewApplicant.addActionListener(bl);
			form.add(btnSubmitNewApplicant, "4, 16");

			btnClear = new JButton("Clear");
			btnClear.addActionListener(bl);
			form.add(btnClear, "6, 16");

			backToMain = new JButton("Back to Main");
			backToMain.addActionListener(bl);
			form.add(backToMain, "8, 16");

			add(form, BorderLayout.CENTER);
		}
	}

	/**
	 * Method to add each candidate from the file into a PriorityQueue
	 * @param allCanidates2
	 * @return - The PriorityQueue edited
	 */
	private PriorityQueue<String> formatCanidatesListToString(LinkedList<Candidate> allCanidates2) {
		PriorityQueue<String> allCanidatesFormatted = new PriorityQueue<String>();
		for (Candidate a : allCanidates) {
			allCanidatesFormatted.add(a.getfName() + " " + a.getlName() + " Age: " + a.getAge() + "  Address: " + a.getAddress());
		}
		return allCanidatesFormatted;
	}

	/**
	 * Method that sorts a LinkedList of Jobs according to the position type. 
	 * @param list - LinkedList of jobs to be sorted
	 * @return - sorted list of jobs
	 */
	public static LinkedList<Job> insertionSort(LinkedList<Job> list){
		Job temp;
		int f = list.size();
		for(int i=0;i<f;i++){
			for(int j=i+1;j<f;j++){
				if(list.get(i).getJobType().compareToIgnoreCase(list.get(j).getJobType())>0){
					temp = list.get(i);
					swapJobs(list, list.get(i), list.get(j));
					list.set(j, temp);
				}
			}
		}
		return list;
	}	
	
	/**
	 * Method that swaps the position of 2 jobs
	 * @param list - list of jobs
	 * @param i - Job 1
	 * @param j - Job 2 
	 */
    public static void swapJobs(LinkedList<Job> list, Job i, Job j){
  
        // Get the positions of the elements
        int index1 = list.indexOf(i);
        int index2 = list.indexOf(j);
  
        // Returning if the element is not present in the LinkedList
        if (index1 == -1 || index2 == -1) {
            return;
        }
  
        // Swap the elements
        list.set(index1, j);
        list.set(index2, i);
    }

    /**
     * Formats all jobs in the Linked List
     * @param allJobs2
     * @return - All jobs formatted 
     */
	private LinkedList<String> formatJobsListToString(LinkedList<Job> allJobs2) {
		LinkedList<String> allJobsFormatted = new LinkedList<String>();
		//Insertion Sort method
		insertionSort(allJobs);
		for (Job a : allJobs) {
			allJobsFormatted.add("Position: " + a.getPosition() + " Type: " + a.getJobType()
					+ " | Shift: " + a.getStartTime() + " - " + a.getEndTime());
		}
		return allJobsFormatted;
	}

	/**
	 * Method that creates an Error pop-up page.
	 * @param message - Error message for input validation
	 */
	private void popUpErrorMessage(String message) {
		final JOptionPane pane = new JOptionPane(message);
		final JDialog d = pane.createDialog((JFrame) null, "Wrong input");
		d.setLocation(10, 10);
		d.setVisible(true);
		sendBackToMainMenuJPanel();

	}

	/**
	 * Sends user Back to home page
	 */
	private void sendBackToMainMenuJPanel() {
		removeAll();
		setVisible(false);
		MainMenuJPanel newPanel = new MainMenuJPanel();
		add(newPanel);
		validate();
		setVisible(true);
		repaint();

	}

	//Add buttonListener
	class buttonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//Submit Button
			if (e.getSource() == btnSubmitNewApplicant) {
				
				//Input validation for text fields
				try {
					String a = textFieldYearsExp.getText();
					String b = textYearsEducation.getText();

					int tempExp = Integer.parseInt(a);
					int tempEdu = Integer.parseInt(b);

					//Creates a Application to add to the file
					Job tempJobs = allJobs.get(jobTextField.getSelectedIndex());
					Candidate tempCanidates = allCanidates.get(canidateTextField.getSelectedIndex());

					Application applicationToAdd = new Application(tempJobs, tempCanidates, tempExp, tempEdu);
					allApplications.add(applicationToAdd);

					applicationFileHelper.writeFile(allApplications);
				} catch (Exception f) {
					popUpErrorMessage("Input for Years Experience and Year of Education must be an integer number and can't be blank.");
				}
				
			//Clear button
			} else if (e.getSource() == btnClear) {
				clearAllFields();
			//Back To Main button
			} else if (e.getSource() == backToMain) {
				sendBackToMainMenuJPanel();
			}

		}

		/**
		 * Cleats each text field
		 */
		private void clearAllFields() {
			textYearsEducation.setText("");
			textFieldYearsExp.setText("");

		}
	}

}
