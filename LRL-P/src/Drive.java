import java.util.ArrayList;

import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;


public class Drive 
{
	private ArrayList<Integer> lengths = new ArrayList<>(); // An array list of blinks' length
	
	public int getLargestValue() throws InterruptedException
	{
		int lvalue = 0; // Variable which holds the duration of the longest blink
		for (int index = 0; index < lengths.size(); index++) // Loops through the array list, searches for the largest value
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

		for (int index = 0; index < lengths.size(); index++) // First get the index of the longest blink
		{
			if (lengths.get(index) > lengths.get(largestValue))
				largestValue = index;
		}

		for (int index = 0; index < lengths.size(); index++) // Find the index of the second longest blink
		{
			if (lengths.get(index) > lengths.get(secondLargest) && index != largestValue)
				secondLargest = index;
		}
		return lengths.get(secondLargest);
	}


	public void hardLengthValue() throws InterruptedException
	{
		LightSensor light = new LightSensor(SensorPort.S2);
		light.setFloodlight(false);
		int counter = 0;

		while (lengths.size() < 15)
		{
			while (light.getLightValue() < 40) // Robot waits for blinking to start
			{
				Thread.sleep(1);
			}

			Thread.sleep(1);				// Start counting blink length
			counter++;

			if (light.getLightValue() < 40) // If the blinking stops add the counter and proceed
			{
				lengths.add(counter);
				counter = 0;
				Thread.sleep(1);
			}

		}

	}

     public void turn() throws InterruptedException // Turn clockwise.
	{
		int largest = this.getLargestValue();
		MotorPort.B.resetTachoCount();
		int tachoCount = MotorPort.B.getTachoCount();
		
		// circumference of the circle which robot makes when it spins = 85 (2*13.5*Math.PI)
		// circumference of the wheel = 17,27
		while (tachoCount < largest/10)
		{
			MotorPort.B.controlMotor(75, 1);
			MotorPort.C.controlMotor(100, 3);
			tachoCount = MotorPort.B.getTachoCount();
		}
		Thread.sleep(1000);
	}

	public void goForward() throws InterruptedException
	{
		int second = this.getSecondLargestValue();
		MotorPort.B.resetTachoCount();
		int tachoCount = MotorPort.B.getTachoCount();
		
		while (tachoCount < second/10) // Time converted to seconds*100
		{
			MotorPort.B.controlMotor(80, 1);
			MotorPort.C.controlMotor(83, 1);
			tachoCount = MotorPort.B.getTachoCount();
		}

		MotorPort.B.controlMotor(100, 3);
		MotorPort.C.controlMotor(100, 3);
		Thread.sleep(1000);
	}

}