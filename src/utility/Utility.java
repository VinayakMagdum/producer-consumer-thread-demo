package utility;

import java.util.Scanner;

/**
 * Class Utility.
 * @author Vinayak
 *
 */
public class Utility
{
	static private Scanner scanner=new Scanner(System.in);
	/**
	 * To get integer input.
	 * @return - input integer value.
	 */
	public static int getIntegerInput()
	{
		int intInput = 0;
		
		try
		{
			String strInput = scanner.nextLine();
			intInput = Integer.parseInt(strInput);
			if(intInput<=0)
			{
				System.err.println(Defination.ERR_WRONG_INPUT);
				intInput=getIntegerInput();
			}
		}
		catch(Exception E)
		{
			System.err.println(Defination.ERR_WRONG_INPUT);
			intInput= getIntegerInput();
		}
		return intInput;
	}
}