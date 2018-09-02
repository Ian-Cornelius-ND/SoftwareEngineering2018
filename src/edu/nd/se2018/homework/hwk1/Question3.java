package edu.nd.se2018.homework.hwk1;

public class Question3 {
	/*
	 * Homework1 Question3:
	 * Calculating max mirrored subsets of integer array.
	 * August 29, 2018
	 * 
	 * @author Ian Cornelius
	 */
	
	public Question3(){}	
	
    public int getMirrorCount(int[] numbers){
   
    	int arrayLength = numbers.length;
    	
        if (arrayLength == 0)
            return 0;
        
        int maxMirrorSize = 1;
        boolean mirrorFound = false;

        for (int i = 0; i < arrayLength; i++){
        	int size = i;
            int tempSize = 1;

            for (int j = arrayLength-1; j>= 0 && (size<arrayLength); j--){
            	
            	/* Finds initial start of mirror and starts tracking size */
                if (numbers[size] == numbers[j] && !mirrorFound){
                    mirrorFound = true;
                    size++;
                    continue;
                }
                
                /* Checks if next number from both directions is equal, increases mirror size */
                if (numbers[size] == numbers[j] && mirrorFound){
                    tempSize++;
                    size++;
                    if(tempSize>maxMirrorSize)
                    	maxMirrorSize = tempSize;
                    continue;
                }
                
                /* Checks if mirror sequence has ended */
                if (numbers[i] != numbers[j] && mirrorFound){
                    mirrorFound = false;
                    size = i;
                    tempSize = 1;
                    continue;
                } 
                
                /* Default if no mirror segments were found */
                if (j == size || (j-size)==1){
                    mirrorFound = false;
                    break;
                }

            }//inner for
        }//outer for
        
        return maxMirrorSize; 
    }
    	
    
    public static void main(String[] args) {
		Question3 mirrorNums = new Question3();
		int result = mirrorNums.getMirrorCount(new int[] {1,2,3,2,1});
		System.out.println("The answer is: " + result);
	}
}
