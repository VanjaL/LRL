import java.util.ArrayList;

import lejos.nxt.LightSensor;
import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;


public class FindLightSource
{

	private ArrayList<Integer> values;
	private int largest;

	public FindLightSource() throws InterruptedException
	{
		values = new ArrayList<>();
		sampleValues();
		findLargest();
		driveToSource();
	}

	public void sampleValues() throws InterruptedException // Sample the light values around itself.
	{
		LightSensor light = new LightSensor(SensorPort.S2);
		MotorPort.B.resetTachoCount();
		int tachoCount = MotorPort.B.getTachoCount();
		int value = 0;
		while(tachoCount <= 1800)
		{
			MotorPort.B.controlMotor(75, 1);
			MotorPort.C.controlMotor(100, 3);
			Thread.sleep(1500);
			value = light.getLightValue();
			values.add(value);
			tachoCount = MotorPort.B.getTachoCount();
		}
	}

	public int findLargest() // Finds the largest value in the array list.
	{
		largest = 0;
		for (int x = 0; x < values.size(); x++) 
		{
			if (values.get(x) > values.get(largest))
				largest = x;
		}

		return values.get(largest);
	}

	public void driveToSource() throws InterruptedException // Finds and drives.
	{
		UltrasonicSensor ultra = new UltrasonicSensor(SensorPort.S4);
		LightSensor light = new LightSensor(SensorPort.S2);
		int value = 0;
		int largest = findLargest();
		while(!(value < largest + 2 && value > largest - 2)) // Finding where was the largest value
		{ 
			MotorPort.B.controlMotor(75, 1); // Permitted +- 2 in value.
			value = light.getLightValue();

		}

		int distance = ultra.getDistance();

		while (distance > 30) // Drives to the source.
		{
			MotorPort.B.controlMotor(80, 1);
			MotorPort.C.controlMotor(83, 1);
			distance = ultra.getDistance();
		}

		MotorPort.B.controlMotor(100, 3);
		MotorPort.C.controlMotor(100, 3);

	}

}
