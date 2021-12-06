package tests;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import java.util.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import model.Job;


class JobTest {

	//Create two Jobs and a LinkedList<String> that will be used to read each file sorted by position name. 
	Job job1 = new Job("Software Developer", "Internship", LocalTime.of(8, 15), LocalTime.of(12, 55), 25.5);
	Job job2 = new Job("Data Analyst", "Full-Time", LocalTime.of(9, 15), LocalTime.of(15, 30), 20.0);
	LinkedList<Job> jobList = new LinkedList<Job>();
	
	//Create Insertion Sort Methods 
	/**
	 * Method that sorts a LinkedList of Jobs according to the position type. 
	 * @param list - LinkedList of jobs to be sorted
	 * @return - sorted list of jobs
	 */
	public static LinkedList<Job> insertionSort(LinkedList<Job> list){
		Job temp;
		int f = list.size();
		for(int i=0;i<f;i++){
			for(int j=i+1;j<f;j++){
				if(list.get(i).getJobType().compareToIgnoreCase(list.get(j).getJobType())>0){
					temp = list.get(i);
					swapJobs(list, list.get(i), list.get(j));
					list.set(j, temp);
				}
			}
		}
		return list;
	}	
	
	/**
	 * Method that swaps the position of 2 jobs
	 * @param list - list of jobs
	 * @param i - Job 1
	 * @param j - Job 2 
	 */
    public static void swapJobs(LinkedList<Job> list, Job i, Job j){
  
        // Get the positions of the elements
        int index1 = list.indexOf(i);
        int index2 = list.indexOf(j);
  
        // Returning if the element is not present in the LinkedList
        if (index1 == -1 || index2 == -1) {
            return;
        }
  
        // Swap the elements
        list.set(index1, j);
        list.set(index2, i);
    }

	
	//Test for correct job inputs
	@Test
	void testCorrectJob() {
		assertEquals("Software Developer", job1.getPosition());
		assertEquals("Internship", job1.getJobType());
		assertEquals(LocalTime.of(8, 15), job1.getStartTime());
		assertEquals(LocalTime.of(12, 55), job1.getEndTime());
		assertEquals(25.5, job1.getHourlywage());
	}
	
	//Test for incorrect inputs
	@Test
	void testIncorrectJob() {
		assertNotEquals("Software Developer", job2.getPosition());
		assertNotEquals("Internship", job2.getJobType());
		assertNotEquals(LocalTime.of(8, 15), job2.getStartTime());
		assertNotEquals(LocalTime.of(12, 55), job2.getEndTime());
		assertNotEquals(25.5, job2.getHourlywage());
	}

	//Test if LinkedList adds jobs correctly. 
	@Test
	void testAddingJobToPriorityQueue() {
		jobList.add(job1);
		jobList.add(job2);
		Job expected = job1;
		Job actual = jobList.getFirst();
		assertEquals(expected, actual);
	}
	
	//Test insertion sort Algorithm
	@Test
	void testInsertionSort1() {
		jobList.add(job1);
		jobList.add(job2);
		insertionSort(jobList);
		Job expected = job2;
		Job actual = jobList.getFirst();
		assertEquals(expected, actual);
	}
	
	@Test
	void testInsertionSort2() {
		jobList.add(job1);
		jobList.add(job2);
		Job job3 = new Job("Cyber-Security Analyst", "Part-Time", LocalTime.of(9, 15), LocalTime.of(15, 30), 20.0);
		jobList.add(job3);
		insertionSort(jobList);
		Job expected = job3;
		Job actual = jobList.getLast();
		assertEquals(expected, actual);
	}
	
	
	//Test size of Linked List
	@Test
	void testLinkedListSize() {
		jobList.add(job1);
		jobList.add(job2);
		int expected= 2;
		int actual = jobList.size();
		assertEquals(expected, actual);
	}
}
