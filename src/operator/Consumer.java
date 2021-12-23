package operator;

import utility.Defination;
import buffer.Buffer;

/**
 * Class Consumer.
 * @author Vinayak
 *
 */
public class Consumer implements Runnable 
{
	public int _totalConsumeSum = 0;
	private Buffer _bufObject;

	public Consumer(Buffer bufObject) 
	{
		this._bufObject = bufObject;
	}

	/**
	 * TO start execution of consumer thread.
	 */
	@Override
	public void run() 
	{
		try
		{
			while(_bufObject._isProducerDead == false || _bufObject._producedData.size()!=0)	
			{
					consumeData();
			}
			System.out.println(Thread.currentThread().getName()+" " + Defination.GOT_TERMINATED);
		}
		catch(Exception e){}
	}

	/**
	 * 
	 * To consume data.
	 * 
	 */
	private void consumeData()
	{
		int cnt = _bufObject.popData();
		if(cnt > 0)
		{
			_totalConsumeSum += cnt;
		}
	}
}