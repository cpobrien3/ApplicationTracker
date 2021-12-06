package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import model.Job;



public class JobFileHelper implements FileHelper{
	String nameOfFile = "jobs.txt";
	File listOfObjects = new File(nameOfFile);
	
	/**
	 * Checks if a file exists
	 * @return boolean - if file exists
	 */
	@Override
	public boolean doesAFileExist() {
		
		if (listOfObjects.exists()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Method that adds a job to a file
	 * @param list - List of jobs
	 * @return if file exists
	 */
	public boolean writeFile(LinkedList<?> list) {
		LinkedList<Job> jobsToWrite = (LinkedList<Job>) (list);
		
		//Throw exception if file doesn't exist
		try {
			PrintWriter listOfObjects = new PrintWriter(nameOfFile);
			
			//Add each job to a file
			for (Job i : jobsToWrite) {
				StringBuilder sb = new StringBuilder();
				sb.append(i.getPosition() + "," + i.getJobType() + ","  + i.getStartTime() + "," + i.getEndTime() + 
						","  + i.getHourlywage()); 
				listOfObjects.println(sb.toString());
			}
			listOfObjects.close();
		} catch (FileNotFoundException e) {
			System.out.println("No file called " + nameOfFile + " found when writing jobs.");
			return false;
		}
		return true;
	}
	
	/**
	 * Method that reads each job from a file
	 * @return List of jobs
	 */
	@Override
	public LinkedList<?> readFile() {
		LinkedList<Job> allJobs= new LinkedList<Job>();
		Scanner fileIn;
		
		//Throw exception if file doesn't exist
		try {
			fileIn = new Scanner(listOfObjects);

			//Add each job from a file into a list
			while (fileIn.hasNextLine()) {
				String value = fileIn.nextLine();
				String[] parts = value.split(",");
				Job currentJob = new Job(parts[0], parts[1], parseTime(parts[2]), parseTime(parts[3]), Double.parseDouble(parts[4]));
				allJobs.add(currentJob);
			}
				fileIn.close();
		} catch (FileNotFoundException e) {
		
			System.out.println("No file called " + nameOfFile + " found when reading Jobs.");
		}
		return allJobs;
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
	
