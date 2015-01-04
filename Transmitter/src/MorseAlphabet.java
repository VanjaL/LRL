

public class MorseAlphabet
{
	/*
	 * Morse Code alphabet. 
	 */
	private String[] alphabet = {
			".-",		// A 
			"-...",		// B
			"-.-.",		// C
			"-..",		// D
			".",		// E
			"..-.",		// F
			"--.",		// G
			"....",		// H
			"..",		// I
			".---",		// J
			"-.-",		// K
			".-..",		// L
			"--",		// M
			"-.",		// N
			"---",		// O
			".--.",		// P
			"--.-",		// Q
			".-.",		// R
			"...",		// S
			"-",		// T
			"..-",		// U
			"...-",		// V
			".--",		// W
			"-..-",		// X
			"-.--",		// Y
			"--.."		// Z
	};

	private String[] morseNumbers = {
			"-----",	// 0
			".----",	// 1
			"..---", 	// 2
			"...--",	// 3
			"....-",	// 4
			".....",	// 5
			"-....",	// 6
			"--...",	// 7
			"---..",	// 8
			"----." 	// 9
	};
	
	public String getCode(char ch)
	{
		if((ch>='A') && (ch<='Z'))
		{
			return alphabet[ch -'A'];
		}
		else if((ch>='a') && (ch<='z'))
		{
			return alphabet[ch -'a'];
		}
		else if((ch>='0') && (ch<='9'))
		{
			return morseNumbers[ch -'0'];
		}
		else
			return " ";
	}

}

