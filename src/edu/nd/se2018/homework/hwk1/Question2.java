package edu.nd.se2018.homework.hwk1;

import java.util.HashMap;

public class Question2 {
	/*
	 * Homework1 Question2:
	 * Returning most frequently occurring word.
	 * August 29, 2018
	 * 
	 * @author Ian Cornelius
	 */

	public Question2(){}
	
	public String getMostFrequentWord(String input, String stopwords){
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		
		String[] inputArray = input.split(" ");
		String[] stopwordsArray = stopwords.split(" ");
		int numberOfMax = 0;
		
		/* For-loop to insert 'input' words into a HashMap */
		for (int i = 0; i < inputArray.length; i++) {
            if (!hm.containsKey(inputArray[i]))
                hm.put(inputArray[i], 1);
            else
                hm.put(inputArray[i], (Integer) hm.get(inputArray[i]) + 1);
        }
		
		
		/* For-loop to insert 'stopwords' words into a HashMap */
		for (int i = 0; i < stopwordsArray.length; i++) {
            if (hm.containsKey(stopwordsArray[i]))
                hm.put(stopwordsArray[i], 0);
        }
				
		/* Checks which string in the HashMap has the highest value
		 * i.e. Which word is most frequent */
		HashMap.Entry<String, Integer> maxValue = null;
		for (HashMap.Entry<String, Integer> entry : hm.entrySet()) {
		  if (maxValue == null || entry.getValue() > maxValue.getValue())
			  maxValue = entry;
		}
		
		/* Checks if multiple strings in the HashMap have an equal highest value */
		for( Integer x : hm.values()) {
			if (maxValue.getValue() == x) {
				numberOfMax = numberOfMax + 1;
			}
		}
		
		/* Returns 'null' if >1 strings have the same highest value */
		if(numberOfMax > 1)
			return null;
		
		return maxValue.getKey();
	}
	
	public static void main(String[] args) {
		Question2 freqWord = new Question2();
		String result = freqWord.getMostFrequentWord("giraffe elephant giraffe tiger tiger", "and a hes the of up but with");
		System.out.println("The answer is: " + result);
	}
}
