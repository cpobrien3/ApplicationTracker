package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

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

import controller.CandidateFileHelper;
import model.Candidate;

public class AddCandidateJPanel extends JPanel {
	// Create text fields and buttons
	private JTextField txtFName;
	private JTextField txtLName;
	private JTextField txtAge;
	private JTextField txtAddress;
	private JButton btnSubmitNewCanidate;
	private JButton btnClearEntry;
	private JButton btnReturnToMain;

	// Create a file helper and a linked list to add to Candidates to a file
	CandidateFileHelper fileHelper = new CandidateFileHelper();
	LinkedList<Candidate> allCanidates;

	public AddCandidateJPanel() {
		allCanidates = (LinkedList<Candidate>) fileHelper.readFile();

		// Create the form
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

		// Input for first name
		JLabel lblFirstName = new JLabel("First Name:");
		form.add(lblFirstName, "4, 4, right, default");
		txtFName = new JTextField();
		form.add(txtFName, "8, 4, fill, default");
		txtFName.setColumns(10);

		// Input for last name
		JLabel lblLastName = new JLabel("Last Name:");
		form.add(lblLastName, "4, 6, right, default");

		txtLName = new JTextField();
		form.add(txtLName, "8, 6, fill, default");
		txtLName.setColumns(10);

		// Input for age
		JLabel lblAge = new JLabel("Age:");
		form.add(lblAge, "4, 8, right, default");

		txtAge = new JTextField();
		form.add(txtAge, "8, 8, fill, default");
		txtAge.setColumns(10);

		// Input for address
		JLabel lblAddress = new JLabel("Address:");
		form.add(lblAddress, "4, 10, right, default");

		txtAddress = new JTextField();
		form.add(txtAddress, "8, 10, fill, default");

		buttonListener bl = new buttonListener();

		// Add the three buttons to the form
		btnSubmitNewCanidate = new JButton("Submit New Candidate");
		btnSubmitNewCanidate.addActionListener(bl);
		form.add(btnSubmitNewCanidate, "8, 14");

		btnClearEntry = new JButton("Clear Entry");
		btnClearEntry.addActionListener(bl);
		form.add(btnClearEntry, "8, 16");

		btnReturnToMain = new JButton("Back to Main");
		btnReturnToMain.addActionListener(bl);
		form.add(btnReturnToMain, "8, 18");

		add(form);

	}

	// Action Listener class
	class buttonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			// Submit button
			if (e.getSource() == btnSubmitNewCanidate) {
				// Input validation for text fields
				if (txtFName.getText().equals("") || txtLName.getText().equals("") || txtAddress.getText().equals("")) {
					popUpErrorMessage("All fields need to have an input!");
					sendBackToMainMenuJPanel();
				} else
					//Input validation for Age
					try {
						String a = txtAge.getText();
						int tempAge = Integer.parseInt(a);

						String address = txtAddress.getText();

						//Add to candidate to the file
						Candidate CanidateToAdd = new Candidate(txtFName.getText(), txtLName.getText(), tempAge,
								address);
						allCanidates.add(CanidateToAdd);
						fileHelper.writeFile(allCanidates);

						clearAllFields();
					} catch (NumberFormatException z) {
						popUpErrorMessage("Age must be input as an Integer Number (0-9)");
					}

			  //Clear button
			} else if (e.getSource() == btnClearEntry) {
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
			txtFName.setText("");
			txtLName.setText("");
			txtAge.setText("");
			txtAddress.setText("");

		}

		/**
		 * Creates a pop up message
		 * @param message - Instructions for a user
		 */
		private void popUpErrorMessage(String message) {
			final JOptionPane pane = new JOptionPane(message);
			final JDialog d = pane.createDialog((JFrame) null, "Wrong input");
			d.setLocation(10, 10);
			d.setVisible(true);
			sendBackToMainMenuJPanel();

		}

		/**
		 * Sends user back to main menu
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
