


public class Controller
{
	public static void main(String[] args) throws InterruptedException
	{
		Driver drive = new Driver();
		drive.hardLengthValue();
		Thread.sleep(1000);
		drive.turn();
		drive.goForward();
				
		LightSourceFinder finder = new LightSourceFinder();
		finder.sampleValues();
		finder.findLargest();
		finder.driveToSource();
		
		
	}
}
