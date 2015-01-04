import lejos.nxt.Button;
import java.util.ArrayList;
import lejos.nxt.LCD;

public class MorseComm
{

	public static int menu()
	{
		int choise=1;
		
		LCD.clear();
		LCD.drawString("MORSE CODE ROBOT", 0, 0);
		LCD.drawString("> TX pulses I", 0, 1);
		LCD.drawString("  TX pulses H", 0, 2);
		LCD.drawString("  TX Morse code", 0, 3);
		while(Button.ENTER.isDown())
			; // Wait for key to be released
		while(!Button.ENTER.isDown() && choise>0)
		{
			if(Button.RIGHT.isDown())
			{
				if(choise<3)
				{
					LCD.drawChar(' ', 0, choise);
					choise++;
					LCD.drawChar('>', 0, choise);
				}
				while(Button.RIGHT.isDown())
					; // Wait for key to be released
			}
			if(Button.LEFT.isDown())
			{
				if(choise>1)
				{
					LCD.drawChar(' ', 0, choise);
					choise--;
					LCD.drawChar('>', 0, choise);
				}
				while(Button.LEFT.isDown())
					; // Wait for key to be released
			}
			if(Button.ESCAPE.isDown())
				choise=0;
		}
		while(Button.ENTER.isDown())
			; // Wait for key to be released
		return choise;
	}
	
	public static void main(String[] args)
	{
		boolean showMenu = true;
		int menuChoise=0;

		MorseEncoder encoder = new MorseEncoder();

		// Pulse train for Hard challenge: Sorted numbers: 1,2,3,4,5,6,9,12,14,25,45
		// Angle = 45*100*0,05s = 225 deg. Distance = 25*100*0,05s = 125 cm.
		int[] pulsesH = {250, 50, 450, 2250, 150, 200, 300, 100, 1250, 700, 600};
		
		// Pulse train for Intermediate challenge: Sorted numbers: 1,2,3,5,6,9,20,24
		// Angle = 24*100*0,05s = 120 deg. Distance = 20*100*0,05s = 100 cm.
		int[] pulsesI = {250, 50, 450, 150, 1200, 300, 100, 1000};
		
		int[] oneSecPause = {-18};
		ArrayList<String> message = new ArrayList<>();
		message.add("Hello You are a genious Goodbye ");
		message.add("1 2 3 4 5 6 7 8 9 0");

		int[] xx={-1};
		encoder.sendDriveData(xx);
		
		while (!Button.ESCAPE.isDown())
		{
			if (showMenu)
			{
				menuChoise = menu();
				showMenu = false;
			}
			switch( menuChoise)
			{
			case 1:
				LCD.clearDisplay();
				LCD.drawString("Intermediate", 1, 1);
				while (!Button.ESCAPE.isDown())
				{
					encoder.sendDriveData(pulsesI);
					encoder.sendDriveData(oneSecPause);
				}
				while(Button.ESCAPE.isDown())
					; // Wait for key to be released
				showMenu = true;
				break;
			case 2:
				LCD.clearDisplay();
				LCD.drawString("Hard", 1, 1);
				while (!Button.ESCAPE.isDown())
					encoder.sendDriveData(pulsesH);
				while(Button.ESCAPE.isDown())
					; // Wait for key to be released
				showMenu = true;
				break;
			case 3:
				while (!Button.ESCAPE.isDown())
				{
					encoder.sendMessage(message.get(0));
//					encoder.sendMessage(message.get(1));
				}
				while(Button.ESCAPE.isDown())
					; // Wait for key to be released
				showMenu = true;
				break;
			default:
				break;
			}
		}
	}
}