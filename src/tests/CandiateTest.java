package tests;
import static org.junit.jupiter.api.Assertions.*;


import java.util.PriorityQueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Candidate;


class CandiateTest {

	//Create two candidates and a Priority Queue that will be used to sort the candidates from the
	//files by name
	Candidate candidate1 = new Candidate("Christian", "O'Brien", 22, "123 Main Street");
	Candidate candidate2 = new Candidate("Alex", "Johnson", 25, "Maple Road");
	PriorityQueue<String> candidatesPQ = new PriorityQueue<String>();
	
	//Test for correct candidate inputs
	@Test
	void testCorrectCandidate() {
		assertEquals("Christian", candidate1.getfName());
		assertEquals("O'Brien", candidate1.getlName());
		assertEquals(22, candidate1.getAge());
		assertEquals("123 Main Street", candidate1.getAddress());
	}
	
	//Test for incorrect inputs
	@Test
	void testInorrectCandidate() {
		assertNotEquals("Chris", candidate1.getfName());
		assertNotEquals("OBrien", candidate1.getlName());
		assertNotEquals(21, candidate1.getAge());
		assertNotEquals("124 Main Street", candidate1.getAddress());
	}

	//Test if Priority Queue adds candidates according to first name. 
	@Test
	void testAddingCandidateToPriorityQueue() {
		candidatesPQ.add(candidate1.toString());
		candidatesPQ.add(candidate2.toString());
		String expected = candidate2.toString();
		String actual = candidatesPQ.peek();
		assertEquals(expected, actual);
	}
	
	//Test size of Priority Queue
	@Test
	void testPriorityQueueSize() {
		candidatesPQ.add(candidate1.toString());
		candidatesPQ.add(candidate2.toString());
		int expected = 2;
		int actual = candidatesPQ.size();
		assertEquals(expected, actual);
	}
}
