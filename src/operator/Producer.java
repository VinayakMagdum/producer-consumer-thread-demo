package operator;

import utility.Defination;
import buffer.Buffer;

/**
 * Class Producer.
 * @author Vinayak
 *
 */
public class Producer implements Runnable 
{
	private int _maxLimitOfProd ;
	private static int _temp =0;
	private Buffer _bufObject ;
	public int _totProduction = 0 ;

	public Producer(int maxLimitOfProd, Buffer bufObject) 
	{
		this._maxLimitOfProd = maxLimitOfProd;
		this._bufObject = bufObject;
	}

	@Override
	/**
	 * To start thread execution.
	 */
	public void run()
	{
		try
		{
			startExecution();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * To start execution of Thread.
	 */
	private void startExecution()
	{
		try
		{
			while(_temp < _maxLimitOfProd)
			{
				int randNumber = createNum();
				_totProduction += randNumber;
				_bufObject.pushData(randNumber);
				_temp++;

			}
			if(_temp == _maxLimitOfProd)
			{
				_bufObject._isProducerDead = true;
				System.out.println(Thread.currentThread().getName()
						+ " "+Defination.GOT_TERMINATED);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * To create random number.
	 * @return - random number.
	 */
	private int createNum()
	{
		int randNumber ;
		randNumber = (int)(Math.random()*100+1);
		return randNumber;
	}
}