package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import model.Candidate;




public class CandidateFileHelper implements FileHelper{
	String nameOfFile = "candidates.txt";
	File listOfObjects = new File(nameOfFile);
	
	/**
	 * Method that checks if a file exists
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
	 * Writes a list of candidates to a file.
	 * @param list - List of candidates
	 * @return if file exists
	 */
	public boolean writeFile(LinkedList<?> list) {
		LinkedList<Candidate> canidatesToWrite = (LinkedList<Candidate>) (list);
		
		//Throw exception if file dosen't exist
		try {
			PrintWriter listOfObjects = new PrintWriter(nameOfFile);
			
			//Add each candidate to a file
			for (Candidate i : canidatesToWrite) {
				StringBuilder sb = new StringBuilder();
				sb.append(i.getfName() + "," + i.getlName() + "," + i.getAge() + "," + i.getAddress()); 
				listOfObjects.println(sb.toString());
			}
			listOfObjects.close();
		} catch (FileNotFoundException e) {
			System.out.println("No file called " + nameOfFile + " found when writing canidates.");
			return false;
		}
		return true;
	}
	
	/**
	 * Reads each candidate from the file
	 * @return LinkedList of candidates
	 */
	@Override
	public LinkedList<?> readFile() {
		LinkedList<Candidate> allCanidates= new LinkedList<Candidate>();
		Scanner fileIn;
		
		//Throw exception if file doen't exist
		try {
			fileIn = new Scanner(listOfObjects);

			//Add each candidate from the file to a LinkedList
			while (fileIn.hasNextLine()) {
				String value = fileIn.nextLine();
				String[] parts = value.split(",");
				Candidate currentJob = new Candidate(parts[0],parts[1], Integer.parseInt(parts[2]), parts[3]);
				allCanidates.add(currentJob);
			}
				fileIn.close();
		} catch (FileNotFoundException e) {
			System.out.println("No file called " + nameOfFile + " found when reading Canidates.");
		}
		return allCanidates;
	}
}
