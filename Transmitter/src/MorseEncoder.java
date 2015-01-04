import lejos.util.Delay;
import lejos.nxt.SensorPort;
import lejos.nxt.LightSensor;
import lejos.nxt.LCD;
import lejos.nxt.Sound;

public class MorseEncoder
{
	public final long UNIT_TIME_MS = 50;
	private final int volume = 100;

	private MorseAlphabet code;	
	private int[] signalCode = new int[10];
	private LightSensor senderLed = new LightSensor(SensorPort.S2);
	
	public MorseEncoder()
	{
		code = new MorseAlphabet();
		LCD.clearDisplay();
		senderLed.setFloodlight(false);
	}

	public void sendMessage(String message)
	{
		char chr;
		int line=0;
		
		LCD.clearDisplay();
		for (int i = 0; i < message.length(); i++)
		{
			line = i/16;
			chr = message.charAt(i);
			LCD.drawChar(chr, i%16, line);	// Debug info only. Comment when released
			sendChar(chr);
		}
	}
	
	public void sendDriveData(int[] pulses)
	{
		int i, tmp[]=new int[2];
		
		for(i=0; i<pulses.length; i++)
		{
			tmp[0]=pulses[i]/(int)UNIT_TIME_MS;
			tmp[1]=-2;	//insert 3 units pause
			sendSignal(tmp, 2);
		}
	}

/*
 * Encoding of dots, dashes and spaces into time units of 'on' and 'off'
 * light.
 * Positive values are time units 'on'.
 * Negative values are time units 'off'.
 */
	private void sendChar(char ch)
	{
		int i;
		
		for(i=0; i<code.getCode(ch).length(); i++)
		{
			switch(code.getCode(ch).charAt(i))
			{
			case '.':
				signalCode[i]=1;	// Implicit 1 time unit 'off' after 'on'
				break;
			case '-':
				signalCode[i]=3;	// Implicit 1 time unit 'off' after 'on'
				break;
			case ' ':
				signalCode[i]=-4;	// 1+4+2=7 time units 'off' after word
				break;
			}
		}
		signalCode[i++] = -2;	// 1+2=3 time units 'off' after letter
		sendSignal(signalCode, i);
	}

	private void sendSignal(int[] signal, int signalLength)
	{
		int j;
		
		LCD.clear(5); // Clear line 5 in LCD display
		
		// signal with the light sensor and beeper
		for(j=0; j<signalLength; j++)
		{
			LCD.drawChar(dashDot(signal[j]), j, 5);
			if (signal[j] > 0)
			{
				senderLed.setFloodlight(true);
				Sound.playTone(1000, (int)(UNIT_TIME_MS * signal[j]), volume);
				Delay.msDelay(UNIT_TIME_MS * signal[j]);
				senderLed.setFloodlight(false);
				Delay.msDelay(UNIT_TIME_MS);
			} else
			{
				senderLed.setFloodlight(false);
				Delay.msDelay(-UNIT_TIME_MS * signal[j]);
			}
		}
	}
	
	private char dashDot(int mCode)
	{
		switch(mCode)
		{
		case 1:
			return '.';
		case 3:
			return '-';
		default:
			return ' ';
		}
	}

}
