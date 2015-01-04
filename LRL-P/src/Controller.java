
public class Controller
{
	public static void main(String[] args) throws InterruptedException
	{
		Drive driver = new Drive();
		driver.hardLengthValue();
		Thread.sleep(1000);
		driver.turn();
		driver.goForward();
				
		FindLightSource finder = new FindLightSource();
	}
}
