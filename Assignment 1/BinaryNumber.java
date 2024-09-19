
// This program assumes the usage of Big-Endian format for binary numbers
// Ex: 1010 = (1 * 2^(3)) + (0 * 2^(2)) + (1 * 2^(1)) + (0 * 2^(0))

import java.lang.Math;
import java.util.Arrays;

public class BinaryNumber
{
 // Array to store the binary number
    private int[] data;
    
 // Flag to indicate overflow
    private boolean overflow = false;

 // Create binary number array, given its length
    public BinaryNumber(int length)
    {
        this.data = new int[length];
        
        for(int i = 0 ; i < length ; i++)
        {
            this.data[i] = 0;
        }
    }
    
 // Create binary number array, given the binary number string
    public BinaryNumber(String str)
    {
        this.data = new int[str.length()];
        
        for(int i = 0 ; i < str.length() ; i++)
        {
            this.data[i] = Character.getNumericValue(str.charAt(i));
        }
    }

 // Get the length of the binary number array 
    public int getLength()
    {
        return this.data.length;
    }
    
 // Get a particular digit from the binary number array. If the index is out of bounds, print so
    public int getDigit(int index)
    {
        if(index >= this.data.length)
        {
            System.out.println("Index is out of bounds");
            return -1;
        }

        return this.data[index];
    }
    
 // Perform right shift on the number by the specified margin and populate the output to the array
    public void shiftR(int amount)
    {
        int[] shiftedSequence = this.reallocate(this.data, this.data.length + amount);        
        this.data = shiftedSequence;
    }
    
 // Add the binary number specified in the formal parameter to the array instance of this class's object
    public void add(BinaryNumber aBinaryNumber)
    {
        if(aBinaryNumber.data.length != this.data.length)
        {
            System.out.println("The numbers to be added are not of the same length");
            return;
        }
        
        this.clearOverflow();
        
        int num1 = Integer.valueOf(this.toString(), 2);
        int num2 = Integer.valueOf(aBinaryNumber.toString(), 2);
        
        String binaryAdditionResult = Integer.toBinaryString(num1 + num2);
        
        // It has not been mentioned in the assignment that the number "should"/"should not" be populated to the array if there is an overflow
        // It just states to return the string "Overflow" from the toString() method if there is an overflow and does not talk about the value being populated to the array
        // So, it is being assumed to return the string "Overflow", while still populating the output result to the array
        if(binaryAdditionResult.length() != aBinaryNumber.data.length)        	
        {        	
            this.overflow = true;
            this.data = new int[binaryAdditionResult.length()];            
        }
        
        for(int i = 0 ; i < binaryAdditionResult.length() ; i++)
        {
            this.data[i] = Character.getNumericValue(binaryAdditionResult.charAt(i));
        }
    }

 // Create string from the binary number instance array. If overflow exists, return "Overflow"
    public String toString()
    {
        if(this.overflow)
        {
            return "Overflow";
        }

        String binaryConverted = "";
        
        for(int i = 0 ; i < this.data.length ; i++)
        {
            binaryConverted += this.data[i];
        }

        return binaryConverted;
    }

 // Convert the binary number (array) to decimal (integer) based on the Base-2 format
    public int toDecimal()
    {
        double sum = 0;
        
        for(int i = 0 ; i < this.data.length ; i++)
        {
            sum += this.data[i] * Math.pow(2, (this.data.length - 1 - i));
        }

        return (int)sum;
    }
    
 // Clear the overflow parameter
    public void clearOverflow()
    {
        this.overflow = false;
    }
    

 // Create a new array from the existing array based on the length specified for right shift by pre-padding with 0's
    private int[] reallocate(int[] original, int newLength)
    {
    	int difference = newLength - original.length;
    	int[] newArray = new int[newLength];
    	
    	for(int i = 0; i < newLength ; i++)
    	{
    		if(i < difference)
    		{
    			newArray[i] = 0;
    		}
    		else
    		{
    			newArray[i] = original[i - difference];
    		}
    	}
    	
        return newArray;
    }    
}