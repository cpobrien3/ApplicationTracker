package tests;

import java.time.LocalTime;
import java.util.LinkedList;

import controller.JobFileHelper;
import model.Job;

public class JobFileHelperTest {
	public static void main(String args[]) {
		// Create Jobs
		Job job1 = new Job("Software Developer", "Internship", LocalTime.of(8, 15), LocalTime.of(12, 55), 25.5);
		Job job2 = new Job("Data Analyst", "Full-Time", LocalTime.of(9, 15), LocalTime.of(15, 30), 20.0);
		Job job3 = new Job("Cyber-Security Analyst", "Part-Time", LocalTime.of(10, 15), LocalTime.of(15, 15), 22.25);

		// Add candidates to linked list
		LinkedList<Job> allJobs = new LinkedList<Job>();
		allJobs.add(job1);
		allJobs.add(job2);
		allJobs.add(job3);

		// Test FileHelper
		JobFileHelper jfh = new JobFileHelper();
		System.out.println("Does a Job file exist? " + jfh.doesAFileExist());
		System.out.println("Writing Jobs to file is successful: " + jfh.writeFile(allJobs));
		System.out.println("Does a Job file exist after writing to it? " + jfh.doesAFileExist());

		LinkedList<Job> addedJob = (LinkedList<Job>) jfh.readFile();
		System.out.println("\nPrinting all items in the Job file");
		for (Job a : addedJob) {
			System.out.println(a.toString());
		}
	}
}
