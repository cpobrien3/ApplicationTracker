package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.ApplicationFileHelper;
import model.Application;


public class ViewAllApplicationsJPanel extends JPanel {
	//Create the text that displays each application
	JTextArea list;
	JScrollPane scroll;
	ApplicationFileHelper fileHelper = new ApplicationFileHelper();
	private JButton btnBackToMain;

	/**
	 * Create the panel.
	 */
	public ViewAllApplicationsJPanel() {
		//Create the panel for each application 
		JPanel form = new JPanel();
		JLabel title = new JLabel("DETAILS FOR ALL APPLICATIONS");

		@SuppressWarnings("unchecked")
		//LinkedList to read each file
		LinkedList<Application> allApplications = (LinkedList<Application>) fileHelper.readFile();
		
		//Displays each application
		String allApplicationsFormatted = formatAllApplications(allApplications);
		JTextArea commentTextArea = new JTextArea(allApplicationsFormatted, 5, 20);

		scroll = new JScrollPane(commentTextArea);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setPreferredSize(new Dimension(450, 200));

		//Add back to Main button
		btnBackToMain = new JButton("Back to Main");
		ButtonListener bl = new ButtonListener();
		btnBackToMain.addActionListener(bl);

		form.setLayout(new BorderLayout());
		form.add(title, BorderLayout.NORTH);
		form.add(scroll, BorderLayout.CENTER);
		form.add(btnBackToMain, BorderLayout.SOUTH);

		add(form);
	}

	//ButtonListner Class
	class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnBackToMain) {
				removeAll();
				setVisible(false);
				MainMenuJPanel newPanel = new MainMenuJPanel();
				add(newPanel);
				validate();
				setVisible(true);

			}

		}
	}

	/**
	 * Method that sorts a LinkedList of Applications according to the years of experience.
	 * @param list - List of applications
	 * @return - sorted list
	 */
	public static LinkedList<Application> insertionSort(LinkedList<Application> list){
		Application temp= list.getFirst();
		int f = list.size();
		for(int i=0;i<f;i++){
			for(int j=i+1;j<f;j++){
				if(list.get(i).getYearsExperience() < list.get(j).getYearsExperience()){
					temp = list.get(i);
					swap(list, list.get(i), list.get(j));
					list.set(j, temp);
				}
			}
		}
		return list;
	}	
	
	/**
	 * Method that swaps the position of two Applications in a list
	 * @param list - LinkedList of applications
	 * @param i - Application 1
	 * @param j - Application 2 
	 */
    public static void swap(LinkedList<Application> list, Application i, Application j){
  
        // Get the positions of the elements
        int index1 = list.indexOf(i);
        int index2 = list.indexOf(j);
  
        // Return if the element is not present in the LinkedList
        if (index1 == -1 || index2 == -1) {
            return;
        }
  
        // Swap the elements
        list.set(index1, j);
        list.set(index2, i);
    }
    
    /**
     * Method that formats each Application in the LinkedList
     * @param c - List of applications
     * @return - Each application as a formatted string
     */
	public String formatAllApplications(LinkedList<Application> c) {
		StringBuilder sb = new StringBuilder();
		//Insertion sort algorithm 
		insertionSort(c);
		if (fileHelper.doesAFileExist()) {
			for (Application a : c) {
				sb.append(a.getJob());
				sb.append("\n " + a.getCandidate());
				sb.append("\n     Years Experience: " + a.getYearsExperience());
				sb.append("\n     Years of College Education: " + a.getYearsUpperEducation());
				sb.append("\n\n");
			}
		} else {
			sb.append("No Applications saved.  Return to previous screen to enter data.");
		}

		return sb.toString();
	}
	
	
}
