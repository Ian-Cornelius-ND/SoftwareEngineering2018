package edu.nd.se2018.homework.hwk1;

import java.util.HashSet;

public class Question1 {
	/*
	 * Homework1 Question1:
	 * Summing all the values in an array, unless a value is repeated.
	 * August 29, 2018
	 * 
	 * @author Ian Cornelius
	 */
	
	public Question1(){}
	
	public int getSumWithoutDuplicates(int[] numbers){
		HashSet<Integer> hs = new HashSet<Integer>();
		int total = 0;
		
		for(int i = 0; i < numbers.length; i++) {
			hs.add(numbers[i]);
		}
		
		for(Integer x : hs) {
			total = total + x;
		}
		
		return total;
	}
	
	public static void main(String[] args) {
		Question1 sumNums = new Question1();
		int result = sumNums.getSumWithoutDuplicates(new int[] {1,2,2,2,2,2,2});
		System.out.println("The answer is: " + result);
	}
}
