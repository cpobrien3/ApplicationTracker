package tests;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.PriorityQueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Application;
import model.Candidate;
import model.Job;


class ApplicationTest {

	//Create three candidates and jobs to add to Applications
	Candidate candidate1 = new Candidate("Christian", "O'Brien", 22, "123 Main Street");
	Candidate candidate2 = new Candidate("Alex", "Johnson", 25, "Maple Road");
	Candidate candidate3 = new Candidate("Billy", "White", 32, "East Ave");
	Job job1 = new Job("Software Developer", "Internship", LocalTime.of(8, 15), LocalTime.of(12, 55), 25.5);
	Job job2 = new Job("Data Analyst", "Full-Time", LocalTime.of(9, 15), LocalTime.of(15, 30), 20.0);
	Job job3 = new Job("Cyber-Security Analyst", "Part-Time", LocalTime.of(10, 15), LocalTime.of(15, 15), 22.25);
	
	//Create three Applications
	Application application1 = new Application(job1, candidate1, 1 , 4);
	Application application2 = new Application(job2, candidate2, 3, 5);
	Application application3 = new Application(job3, candidate3, 2, 2);
	
	//Create LinkedList that will be be used to read applications file
	LinkedList<Application> applicationList = new LinkedList<Application>();
	
	//Create insertion sort algorithms
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
	
	//Test for correct Application inputs
	@Test
	void testCorrectApplication() {
		assertEquals(job1, application1.getJob());
		assertEquals(candidate1, application1.getCandidate());
		assertEquals(1, application1.getYearsExperience());
		assertEquals(4, application1.getYearsUpperEducation());
	}
	
	//Test for incorrect inputs
	@Test
	void testInorrectApplication() {
		assertNotEquals(job1, application2.getJob());
		assertNotEquals(candidate1, application2.getCandidate());
		assertNotEquals(1, application2.getYearsExperience());
		assertNotEquals(4, application2.getYearsUpperEducation());
	}

	//Test if LinkList adds Application. 
	@Test
	void testAddingApplication() {
		applicationList.add(application1);
		applicationList.add(application2);
		Application expected = application1;
		Application actual = applicationList.getFirst();
		assertEquals(expected, actual);
	}
	
	//Test size of LinkedList
	@Test
	void testLinkedListSize() {
		applicationList.add(application1);
		applicationList.add(application2);
		applicationList.add(application3);
		int expected = 3;
		int actual = applicationList.size();
		assertEquals(expected, actual);
	}
	
	//Test Insertion sort methods 
	@Test
	void testInsertionSortApplication1() {
		applicationList.add(application1);
		applicationList.add(application2);
		applicationList.add(application3);
		insertionSort(applicationList);
		Application expected = application2;
		Application actual = applicationList.getFirst();
		assertEquals(expected, actual);
	}
	
	@Test
	void testInsertionSortApplication2() {
		applicationList.add(application1);
		applicationList.add(application2);
		applicationList.add(application3);
		insertionSort(applicationList);
		Application expected = application1;
		Application actual = applicationList.getLast();
		assertEquals(expected, actual);
	}
}
