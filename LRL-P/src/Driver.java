import java.util.ArrayList;

import lejos.nxt.LightSensor;
import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;


public class Driver 
{

	private ArrayList<Integer> lengths = new ArrayList<>(); // An array list of blinks' length
	private final double CIRCUMFERENCE = (5.6*Math.PI);   // Circumference of the wheel
	
	// Reads the lengths of blinks
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


	// Gets the largest value from the ArrayList lengths 
	public int getLargestValue() throws InterruptedException
	{
		int lvalue = 0; 									// Variable which holds the duration of the longest blink
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
	
	// Gets the second largest value
	public int getSecondLargestValue() throws InterruptedException
	{
		int largestValue = 0;
		int secondLargest = 0;

		for (int index = 0; index < lengths.size(); index++) // First get the index of the longest blink
		{
			if (lengths.get(index) > lengths.get(largestValue))
				largestValue = index;
		}

		for (int index = 0; index < lengths.size(); index++) // Then find the index of the second longest blink
		{
			if (lengths.get(index) > lengths.get(secondLargest) && index != largestValue)
				secondLargest = index;
		}
		return lengths.get(secondLargest);
	}

	     public void turn() throws InterruptedException // Turn clockwise.
	{
		double largest = this.getLargestValue()/10;
		MotorPort.B.resetTachoCount();
		int tachoCount = MotorPort.B.getTachoCount();
		double distance = (CIRCUMFERENCE / 360) *largest;
		// circumference of the circle which robot makes when it spins = 85 (2*13.5*Math.PI)
		// circumference of the wheel = 17,27
		while (tachoCount < distance)
		{
			MotorPort.B.controlMotor(75, 1);
			MotorPort.C.controlMotor(100, 3);
			tachoCount = MotorPort.B.getTachoCount();
		}
		
		Thread.sleep(1000);
	}

	public void goForward() throws InterruptedException
	{
		double second = this.getSecondLargestValue()/10; // Divided by 1000 = seconds, times a 100 = assignment
		MotorPort.B.resetTachoCount();
		double tachoCount = MotorPort.B.getTachoCount();
		double distance = (second/CIRCUMFERENCE)*360;
		while (tachoCount < distance)
		
		{
			MotorPort.B.controlMotor(80, 1);
			MotorPort.C.controlMotor(83, 1);
			tachoCount = MotorPort.B.getTachoCount();
		}
		
		MotorPort.B.controlMotor(100, 3);
		MotorPort.C.controlMotor(100, 3);
		Thread.sleep(1000);
	}
	
	public void backUp()
	{
		MotorPort.B.resetTachoCount();
		int tachoCount = MotorPort.B.getTachoCount();
		
		while (tachoCount > -(50/CIRCUMFERENCE)*360)
		{
			MotorPort.B.controlMotor(80, 2);
			MotorPort.C.controlMotor(83, 2);
			tachoCount = MotorPort.B.getTachoCount();
		}
		
		MotorPort.B.resetTachoCount();
		MotorPort.B.controlMotor(100, 3);
		MotorPort.C.controlMotor(100, 3);
	}

}