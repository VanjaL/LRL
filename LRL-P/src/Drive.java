import lejos.nxt.MotorPort;


public class Drive 
{
	private Light light;

	public Drive() throws InterruptedException
	{
		light = new Light();
		light.hardLengthValue(); // Reads the blinks, store them in an array list, writes the number of blinks on LCD
		turn();
		goForward();
	}

	public void turn() throws InterruptedException // Turn clockwise.
	{
		int largest = light.getLargestValue();
		MotorPort.B.resetTachoCount();
		int tachoCount = MotorPort.B.getTachoCount();
		
		// circumference of the circle which robot makes when it spins = 85 (2*13.5*Math.PI)
		// circumference of the wheel = 17,27
		while (tachoCount < (85*(largest/1000) * 100) / (360*17.27) )
		{
			MotorPort.B.controlMotor(80, 1) ;
			tachoCount = MotorPort.B.getTachoCount();
		}
		Thread.sleep(1000);
	}

	public void goForward() throws InterruptedException
	{
		int second = light.getSecondLargestValue();
		MotorPort.B.resetTachoCount();
		int tachoCount = MotorPort.B.getTachoCount();
		
		// Because the time is miliseconds,it first converts it to seconds
		// Times 100 (said in the assignment)
		while (tachoCount < ((second/1000)* 100 * 360) / 17.27)
		{
			MotorPort.B.controlMotor(80, 1);
			MotorPort.C.controlMotor(80, 1);
			tachoCount = MotorPort.B.getTachoCount();
		}

		MotorPort.B.controlMotor(100, 3);
		MotorPort.C.controlMotor(100, 3);
		Thread.sleep(1000);
	}

}