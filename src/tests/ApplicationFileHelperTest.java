package tests;

import java.time.LocalTime;
import java.util.LinkedList;

import controller.ApplicationFileHelper;
import model.Application;
import model.Candidate;
import model.Job;



public class ApplicationFileHelperTest {
	public static void main(String[] args) {
		
		//Create Jobs
		Job job1 = new Job("Software Developer", "Internship", LocalTime.of(8, 15), LocalTime.of(12, 55), 25.5);
		Job job2 = new Job("Data Analyst", "Full-Time", LocalTime.of(9, 15), LocalTime.of(15, 30), 20.0);
		Job job3 = new Job("Cyber-Security Analyst", "Part-Time", LocalTime.of(10, 15), LocalTime.of(15, 15), 22.25);
		
		//Create Candidates
		Candidate candidate1 = new Candidate("Christian", "O'Brien", 22, "123 Main Street");
		Candidate candidate2 = new Candidate("Alex", "Johnson", 25, "Maple Road");
		Candidate candidate3 = new Candidate("Billy", "White", 32, "East Ave");
		
		//Create Applications
		Application application1 = new Application(job1, candidate1, 1 , 4);
		Application application2 = new Application(job2, candidate2, 3, 5);
		Application application3 = new Application(job3, candidate3, 2, 2);
		
		//Add applications to linked list
		LinkedList<Application> allApplications = new LinkedList<Application>();
		allApplications.add(application1);
		allApplications.add(application2);
		allApplications.add(application3);
		
		//Test if FileHelper functions correctly
		ApplicationFileHelper afh = new ApplicationFileHelper();
		System.out.println("Does a Application file exist? " + afh.doesAFileExist());
		System.out.println("Writing application to file is successful: " + afh.writeFile(allApplications));
		System.out.println("Does a Application file exist after writing to it? " + afh.doesAFileExist());
		
		LinkedList<Application> readApplicationFromFile = (LinkedList<Application>) afh.readFile();
		System.out.println("\nPrinting all items in the Course file");
		for(Application a: readApplicationFromFile){
			System.out.println(a.toString());
		}
	}
}
