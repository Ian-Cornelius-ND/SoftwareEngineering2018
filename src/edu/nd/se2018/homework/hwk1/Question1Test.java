package edu.nd.se2018.homework.hwk1;
import org.junit.Test;

public class Question1Test {
	/*
	 * Homework1 Question1:
	 * Test case provided.
	 * 
	 * @author Ian Cornelius
	 */
	
	@Test
	public void test() {
		Question1 question = new Question1();
		assert(question.getSumWithoutDuplicates(new int[] {1,2,2,2,2,2,2})==3);
		assert(question.getSumWithoutDuplicates(new int[] {1,2,3,1,2,3})==6);
		assert(question.getSumWithoutDuplicates(new int[]{-3,-2,-1,1,2,3})==0);
		assert(question.getSumWithoutDuplicates(new int[] {99,-5,5,13})==112);
	}
}
