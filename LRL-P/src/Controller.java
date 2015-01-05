
public class Controller
{
	public static void main(String[] args) throws InterruptedException
	{
		Driver drive = new Driver();
		drive.hardLengthValue();
		drive.turn();
		drive.goForward();
				
		LightSourceFinder finder = new LightSourceFinder();
		finder.sampleValues();
		finder.findLargest();
		finder.driveToSource();
		
		MorseReader reader = new MorseReader();
		reader.read();
		drive.backUp();
		reader.beep();
	}
}
