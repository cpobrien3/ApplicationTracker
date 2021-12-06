package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.components.TimePickerSettings.TimeIncrement;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import controller.JobFileHelper;

import model.Job;

public class AddJobJPanel extends JPanel {
	//Create text fields, time pickers, and buttons
	private JTextField textFieldPosition;
	private JTextField textFieldJobType;
	private TimePicker startTimePicker;
	private TimePicker endTimePicker;
	private JTextField textFieldHourlyWage;
	private JButton btnSubmitNewJob;
	private JButton btnReturnToMain;
	private JButton btnClear;

	//Create file helper and Linked List to write jobs to a file
	JobFileHelper fileHelper = new JobFileHelper();
	LinkedList<Job> allJobs;

	//Create the panel
	public AddJobJPanel() {
		allJobs = (LinkedList<Job>) fileHelper.readFile();
		JPanel form = new JPanel();
		form.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		//Input for Job Position
		JLabel lblPosition = new JLabel("Position:");
		form.add(lblPosition, "4, 6, right, default");

		textFieldPosition = new JTextField();
		form.add(textFieldPosition, "8, 6, fill, default");
		textFieldPosition.setColumns(10);

		//Input for Job Type
		JLabel lblType = new JLabel("Job Type:");
		form.add(lblType, "4, 8, right, default");

		textFieldJobType = new JTextField();
		form.add(textFieldJobType, "8, 8, fill, default");
		textFieldJobType.setColumns(10);

		//Create a time picker
		TimePickerSettings timeSettings = new TimePickerSettings();
		timeSettings.use24HourClockFormat();
		timeSettings.initialTime = LocalTime.of(15, 30);
		timeSettings.generatePotentialMenuTimes(TimeIncrement.FifteenMinutes, null, null);

		//Input for start time
		JLabel lblStartTime = new JLabel("Start Time:");
		form.add(lblStartTime, "2, 10, right, default");
		startTimePicker = new TimePicker(timeSettings);
		form.add(startTimePicker, "4, 10, left, default");

		//Input for end time 
		JLabel lblEndTime = new JLabel("End Time:");
		form.add(lblEndTime, "6, 10, left, default");
		endTimePicker = new TimePicker(timeSettings);
		form.add(endTimePicker, "8, 10, left, default");

		//Input for hourly wage
		JLabel lblWage = new JLabel("Hourly Wage:");
		form.add(lblWage, "4, 12, right, default");

		textFieldHourlyWage = new JTextField();
		form.add(textFieldHourlyWage, "8, 12, fill, default");
		textFieldHourlyWage.setColumns(10);

		buttonListener bl = new buttonListener();

		//Add submit, clear, and back to home page buttons
		btnSubmitNewJob = new JButton("Submit New Job");
		btnSubmitNewJob.addActionListener(bl);
		form.add(btnSubmitNewJob, "8, 14");

		btnClear = new JButton("Clear Entry");
		btnClear.addActionListener(bl);
		form.add(btnClear, "8, 16");
		
		btnReturnToMain = new JButton("Back to Main");
		btnReturnToMain.addActionListener(bl);
		form.add(btnReturnToMain, "8, 18");

		add(form);
	}

	//Button Listener class
	class buttonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//Submit button
			if (e.getSource() == btnSubmitNewJob) {
				//Input validation for text fields
				if (textFieldPosition.getText().equals("") || textFieldJobType.getText().equals("")) {
					popUpErrorMessage("All fields need to have an input");
					sendBackToMainMenuJPanel();
				} else
					//Input validation for wage
					try {
						String a = textFieldHourlyWage.getText();
						double tempWage = Double.parseDouble(a);

						LocalTime tempStartTime = startTimePicker.getTime();
						LocalTime tempEndTime = endTimePicker.getTime();

						//Create a job and add it to the file
						Job jobToAdd = new Job( textFieldPosition.getText(), textFieldJobType.getText(), tempStartTime, tempEndTime, tempWage);
						allJobs.add(jobToAdd);
						fileHelper.writeFile(allJobs);

						clearAllFields();
					} catch (NumberFormatException f) {
						popUpErrorMessage("Hourly Wage must be entered in as a number(0-9)");
						sendBackToMainMenuJPanel();
					}
			 //Clear button
			} else if (e.getSource() == btnClear) {
				clearAllFields();
			 //Back to Main button
			} else if (e.getSource() == btnReturnToMain) {

				removeAll();
				setVisible(false);
				MainMenuJPanel newPanel = new MainMenuJPanel();
				add(newPanel);
				validate();
				setVisible(true);
			}

		}

		/**
		 * Clears each text field
		 */
		private void clearAllFields() {
			textFieldPosition.setText("");
			textFieldJobType.setText("");
			textFieldHourlyWage.setText("");

		}

		/**
		 * Creates pop up message
		 * @param message - instructions for a user
		 */
		private void popUpErrorMessage(String message) {

			final JOptionPane pane = new JOptionPane(message);
			final JDialog d = pane.createDialog((JFrame) null, "Wrong input");
			d.setLocation(10, 10);
			d.setVisible(true);
			sendBackToMainMenuJPanel();

		}

		/**
		 * Sends user to the home page
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
	}
}
