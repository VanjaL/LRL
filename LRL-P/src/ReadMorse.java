import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

import java.util.ArrayList;

public class ReadMorse {
	private String letter;
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
	    
		// For one word
	     do
	     {
	    	 while (light.getLightValue() < 40)
	    	 { 
	    		 Thread.sleep(1);
	    		 dark ++; 
	    		 
	    		 if (light.getLightValue() > 40)
	    		 {
	    			 readDark(dark); 
	    			 dark = 0; 
	    			 Thread.sleep(1); 
	    		 }
	    	 }

	    	 while (light.getLightValue() > 40)
	    	 { 
	    		 Thread.sleep(1);
	    		 blinkLength ++; 
	    		 
	    		 if (light.getLightValue() < 40)
		         {
	    			readBlinks(blinkLength); 
		            blinkLength = 0; 
		            Thread.sleep(1);
		         }
	    	 }
	     }
	     
	     while (!(dark > 145 && dark < 155));
	  
	}
	
	private void readBlinks(int blink)
	{
		if (blink > 45 && blink < 55)
		{
			letter += ".";
		}
		
		if (blink > 95 && blink < 105)
		{
			letter += "-";
		}	
	}
	
	private void readDark(int pause)
	{
		if (pause > 45 && pause < 55)
		{
			letter += "";
		}
	}
	
	private void decode(String letter)
	{
		for (int i = 0; i < alphabet.length; i++)
		{
			
		}
	}
}
