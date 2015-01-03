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
		while (tachoCount < largest/2 )
		{
			MotorPort.B.controlMotor(80, 1) ;
			tachoCount = MotorPort.B.getTachoCount();
		}
	}

	public void goForward() throws InterruptedException
	{
		int second = light.getSecondLargestValue();
		MotorPort.B.resetTachoCount();
		int tachoCount = MotorPort.B.getTachoCount();
		while (tachoCount < second*2)
		{
			MotorPort.B.controlMotor(80, 1);
			MotorPort.C.controlMotor(80, 1);
			tachoCount = MotorPort.B.getTachoCount();
		}

		MotorPort.B.controlMotor(100, 3);
		MotorPort.C.controlMotor(100, 3);

	}

	/**public static void main(String[] args) throws InterruptedException
{
Drive drive = new Drive();
//FindLightSource light = new FindLightSource();
}**/


}