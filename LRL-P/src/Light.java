import java.util.ArrayList;

import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

public class Light
{
	private ArrayList<Integer> lengths = new ArrayList<>(); // An array list of blinks' length

	public int getLargestValue() throws InterruptedException
	{
		int lvalue = 0; // variable which holds the duration of the longest blink
		for (int index = 0; index < lengths.size(); index++) // Loops through the array list, searching for the largest value
		{
			for (int i = 1; i < lengths.size(); i++)
			{
				if (lengths.get(index) < lengths.get(i))
					lvalue = lengths.get(i);
			}
		}

		return lvalue;
	}

	public int getSecondLargestValue() throws InterruptedException
	{
		int largestValue = 0;
		int secondLargest = 0;

		for (int index = 0; index < lengths.size(); index++) // We first get the index of the longest blink
		{
			if (lengths.get(index) > lengths.get(largestValue))
				largestValue = index;
		}

		for (int index = 0; index < lengths.size(); index++) // And than find the index of the second longest blink
		{
			if (lengths.get(index) > lengths.get(secondLargest) && index != largestValue)
				secondLargest = index;
		}
		return lengths.get(secondLargest);
	}


	public void hardLengthValue() throws InterruptedException
	{
		LightSensor light = new LightSensor(SensorPort.S2);
		int counter = 0;

		while (lengths.size() < 15)
		{
			while (light.getLightValue() < 40)
			{
				Thread.sleep(1);
			}

			Thread.sleep(1);
			counter++;

			if (light.getLightValue() < 40)
			{
				lengths.add(counter);
				counter = 0;
				Thread.sleep(1);
			}

		}

		LCD.drawInt(lengths.size(), 1, 1);
		Thread.sleep(1000);
	}

/**	public static void main(String[] args) throws InterruptedException
	{
		Light c = new Light();
		c.hardLengthValue();
	} **/
}