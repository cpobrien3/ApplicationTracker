package tests;

import java.util.ArrayList;
import java.util.LinkedList;

import controller.CandidateFileHelper;
import model.Candidate;

public class CandidateFileHelperTest {
	public static void main(String args[]) {
		//Create Candidates
		Candidate candidate1 = new Candidate("Christian", "O'Brien", 22, "123 Main Street");
		Candidate candidate2 = new Candidate("Alex", "Johnson", 25, "Maple Road");
		Candidate candidate3 = new Candidate("Billy", "White", 32, "East Ave");
		
		//Add candidates to linked list
		LinkedList<Candidate> allCandiates = new LinkedList<Candidate>();
		allCandiates.add(candidate1);
		allCandiates.add(candidate2);
		allCandiates.add(candidate3);
		
		//Test FileHelper
		CandidateFileHelper cfh = new CandidateFileHelper();
		System.out.println("Does a Candidate file exist? " + cfh.doesAFileExist());
		System.out.println("Writing candidates to file is successful: " + cfh.writeFile(allCandiates));
		System.out.println("Does as candiate file exist after writing to it? " + cfh.doesAFileExist());
		
		LinkedList<Candidate> addedCandidate = (LinkedList<Candidate>)cfh.readFile();
		System.out.println("\nPrinting all items in the Candidiate file");
		for(Candidate a: addedCandidate){
			System.out.println(a.toString());
		}
	}
}
