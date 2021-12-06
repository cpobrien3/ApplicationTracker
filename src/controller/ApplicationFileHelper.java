package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import model.Application;
import model.Candidate;
import model.Job;


public class ApplicationFileHelper implements FileHelper{
	//Create a file to store the application info
	String nameOfFile = "applications.txt";
	File applicationList = new File(nameOfFile);
	
	/**
	 * Checks if file exists for applications
	 * @return if file exists
	 */
	@Override
	public boolean doesAFileExist() {
		if (applicationList.exists()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Reads each application stored in the LinkList
	 * @return The application LinkedList
	 */
	@Override
	public LinkedList<?> readFile() {

		LinkedList<Application> allApplications = new LinkedList<Application>();
		Scanner fileIn;
		
		//Throws exception if file dosen't exist
		try {
			fileIn = new Scanner(applicationList);

			//Read each line of the file and add the application to the LinkedList
			while (fileIn.hasNextLine()){
				String value = fileIn.nextLine();
				String[] parts = value.split("\\|");
				
				Job tempJob = getJob(parts[0]);
				Candidate tempCandidate = getCandidate(parts[1]);
				
				Application current = new Application(tempJob, tempCandidate, Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
				allApplications.add(current);
			}
			fileIn.close();

		} catch (FileNotFoundException e) {
			
		}

		return allApplications;
}



	/**
	 * Method that converts a candidate string from a file into a Candidate object
	 * @param string - candidate as a string
	 * @return candidate as a  Candidate object
	 */
	private Candidate getCandidate(String candidate) {
		String []parts = candidate.split("[\\[=,\\]]");
		Candidate returnedCandidiate = new Candidate(parts[2],parts[4], Integer.parseInt(parts[6]), parts[8]);
		
		return returnedCandidiate;
	}

	/**
	 * Method that converts a job string from a file into a Job object
	 * @param string - Job as a string
	 * @return Job as a Job object
	 */
	private Job getJob(String string) {
		String []parts = string.split("[\\[=,\\]]");
		Job returnedJob = new Job(parts[2], parts[4], parseTime(parts[6]), parseTime(parts[8]), Double.parseDouble(parts[10]));
		
		return returnedJob;
	}


	/**
	 * Method that writes an application to a file
	 * @param list - List of applications to write
	 * @return if file exists
	 */
	public boolean writeFile(LinkedList<?> list) {
		LinkedList<Application> applicationToWrite = (LinkedList<Application>)(list);
		
		//Throw exception if is not found
		try {
			PrintWriter courseFile = new PrintWriter(nameOfFile);
			
			//Add each application to a file
			for(Application c: applicationToWrite){
				StringBuilder sb = new StringBuilder();
				sb.append(c.getJob() + "|" +c.getCandidate() + "|" + c.getYearsExperience() + "|" + c.getYearsUpperEducation() );
				courseFile.println(sb.toString());
			}
			
			courseFile.close();
		} catch (FileNotFoundException e) {
			return false;
		} 
		return true;
	}
	
	/**
	 * Method to convert string to a time
	 * @param toSplit - string to split
	 * @return parsedTime - LocalTime variable
	 */
	private LocalTime parseTime(String toSplit){
		String[] sTime = toSplit.split(":");
		LocalTime parsedTime = LocalTime.of(Integer.parseInt(sTime[0]), Integer.parseInt(sTime[1]));
		return parsedTime;
	}
}
