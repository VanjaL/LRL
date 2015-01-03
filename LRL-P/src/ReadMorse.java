import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

import java.util.ArrayList;

public class ReadMorse {
	private String letter;
	private String word;
	private ArrayList<String> message; 
	
	private String[] alphabet = {
			".-",		// A 
			"-...",		// B
			"-.-.",		// C
			".--",		// D
			".",		// E
			"..-.",		// F
			"--.",		// G
			"....",		// H
			"..",		// I
			".---",		// J
			"-.-",		// L
			"--",		// M
			"-.",		// N
			"---",		// O
			".--.",		// P
			"--.-",		// Q
			".-.",		// R
			"...",		// S
			"-",		// T
			"..-",		// U
			"...-",		// V
			".--",		// W
			"-..-",		// X
			"-.--",		// Y
			"--.."		// Z
	};

	private String[] letters ={"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	private String[] numbers ={"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
	
	private String[] morseNumbers = {
			"-----",	// 0
			".----",	// 1
			"..---", 	// 2
			"...--",	// 3
			"....-",	// 4
			".....",	// 5
			"-....",	// 6
			"--...",	// 7
			"---..",	// 8
			"----." 	// 9
	};
	
	public ReadMorse() throws InterruptedException
	{
		LightSensor light = new LightSensor(SensorPort.S2);
		int blinkLength = 0;
		int dark = 0; 
	    message = new ArrayList<String>(); 
		
		while (light.getLightValue() > 40)
		{
			Thread.sleep(1);
		}
		
		//For the whole message
		// It looks at the blinks as long as the last word in an array list is not "goodbye"
		while (!message.get(message.size() - 1).equalsIgnoreCase("goodbye"))
		{
			
			// For a word
			while (light.getLightValue() < 40 && !(dark > 345 && dark < 355))
		     { 
				// For one letter
				 while (light.getLightValue() < 40 && !(dark > 145 && dark < 155))
			     { 
			    		 Thread.sleep(1);
			    		 dark ++; 
			    		 
			    		 if (light.getLightValue() > 40 && dark > 45 && dark < 55)
			    		 {
			    			this.letter += "";
			    			dark = 0;
			    		 }
			    		 
			    	     while (light.getLightValue() > 40)
			    	     { 
				    		 Thread.sleep(1);
				    		 blinkLength ++; 
				    		 
				    		 if (light.getLightValue() < 40)
					         {
				    			 if (blinkLength > 45 && blinkLength < 55)
				    				{
				    					this.letter += ".";
				    				}
				    				
				    			if (blinkLength > 95 && blinkLength < 105)
				    				{
				    					this.letter += "-";
				    				}
				    			
					            blinkLength = 0; 
					            Thread.sleep(1);
					         }
			    	 }
			   }
		        
				 // To be sure it will do this action only when it exits the inner loop. 
				if (light.getLightValue() > 40)
				{
					// Get the letter/number from the code.
					this.letter = decode(this.letter);
					// Add it to the message.
					word += this.letter;
					// Set the variable to null so it can store new values from the beginning. 
					this.letter = null;
				}
				
				// Keep counting the dark value to be sure when to exit the outer loop.
				dark ++; 
				Thread.sleep(1);
				
			}
			
			// When it exits the outer loop, it means one word is over, adds the word to the message only if the message had begun with "hello" or the word itself is "hello". 
			if (message.get(0).equalsIgnoreCase("hello") || word.equalsIgnoreCase("hello"));
				message.add(word);
		}
	}
	
	// It loops through the array of codes for each letter and number and checks if it is the same as the String given.
	private String decode(String letter)
	{
		for (int i = 0; i < alphabet.length; i++)
		{
			if (alphabet[i].equals(letter))
				this.letter = letters[i];
		}
		
		for (int i = 0; i < morseNumbers.length; i++)
		{
			if (morseNumbers[i].equals(letter))
				this. letter = numbers[i]; 	
		}
		return this.letter;
	}
}
