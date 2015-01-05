import java.util.ArrayList;

import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;


public class MorseReader 
{
	String letter = "";
	String word = "";
	ArrayList<Integer> blinks = new ArrayList<Integer>();  

	String[] alphabet = {
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

	String[] letters ={"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	String[] numbers ={"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

	String[] morseNumbers = {
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
	
	public void read() throws InterruptedException
	{
	LightSensor light = new LightSensor(SensorPort.S2);
	light.setFloodlight(false);
	int blinkLength = 0;
	int dark = 0; 

	/** One word consists of a few letters  
	 * 	One letter consists of one unit of dots and dashes
	 * 	One unit of dots and dashes is read from blink lengths
	 */
	
	// Test, to get one word of 4 letters
	while (word.length() < 4)
	{
		// Between letters
		while (dark < 100 && light.getLightValue() < 40)
		{
			dark = 0;

			//Between part of a letter
			while(dark < 50 && light.getLightValue() < 40)
			{
				dark++;	
				Thread.sleep(1); 
	
				while (light.getLightValue() >= 45)
				{
					Thread.sleep(1);
					blinkLength ++;
					
					if(light.getLightValue() < 45)
					{
						blinks.add(blinkLength);
						blinkLength = 0;
						dark = 0;
					}
				}
			}
			
			for (int i = 0; i < blinks.size(); i++ )
			{ 
				if (blinks.get(i) <= 50)
				{
					letter += ".";
				}

				else 
					letter += "-";

				Thread.sleep(1);

			}
		}
		
		for (int i = 0; i < alphabet.length; i++)
		{
			if (alphabet[i].equals(letter))
				letter = letters[i];
			Thread.sleep(1);

		}
		word += letter;

	}

	LCD.clear();
	LCD.drawString(word, 1, 1);
	Thread.sleep(2000);

	}
	
	public void beep() throws InterruptedException
	{
		for (int index = 0; index < 3; index++)
		{
			Thread.sleep(500);
			
			Sound.playTone(2000, 50, 75);
			Thread.sleep(100);
			Sound.playTone(2000, 50, 75);
			Thread.sleep(100);
			Sound.playTone(2000, 50, 75);
			Thread.sleep(150);
		
			Sound.playTone(2000, 100, 75);
			Thread.sleep(150);
			Sound.playTone(2000, 100, 75);
			Thread.sleep(150);
			Sound.playTone(2000, 100, 75);
			Thread.sleep(200);
		
			Sound.playTone(2000, 50, 75);
			Thread.sleep(100);
			Sound.playTone(2000, 50, 75);
			Thread.sleep(100);
			Sound.playTone(2000, 50, 75);
			Thread.sleep(150);
		}
	}
}
