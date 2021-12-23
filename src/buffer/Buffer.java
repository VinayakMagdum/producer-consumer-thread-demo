package buffer;

import java.util.ArrayList;
import utility.Defination;

/**
 * Class Buffer
 * @author Vinayak
 *
 */
public class Buffer
{
	public final ArrayList<Integer> _producedData;
	private int _sizeOfBuffer;
	public boolean _isProducerDead = false;
	
	public Buffer(int sizeOfBuffer)
	{
		_sizeOfBuffer = sizeOfBuffer;
		_producedData = new ArrayList<Integer>(sizeOfBuffer);
	}

	/**
	 * TO check arraylist is empty or not.
	 * @return
	 */
	private boolean isEmpty()
	{
		return _producedData.isEmpty();
	}

	/**
	 * TO chech arraylist is full or not.
	 * @return
	 */
	private boolean isFull()
	{
		return _producedData.size()== _sizeOfBuffer;
	}

	/**
	 * To push number in arrayList.
	 * @param number - number to add in arrayList.
	 */
	public void pushData(int number)
	{
		synchronized(_producedData)
		{
			while(isFull())
			{
				try
				{
					System.out.println(Thread.currentThread().getName()+Defination.IN_WAIT);
					_producedData.wait();
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			System.out.println(Defination.ELE_PRODUCED + number);
			_producedData.add(number);
			_producedData.notifyAll();
		}
	}

	/**
	 * To pop number from arrayList
	 * @return - removed number 
	 */
	public int popData()
	{
		int popNumber = 0;
		synchronized(_producedData)
		{
			while(isEmpty())
			{
				if(_isProducerDead)
				{
					_producedData.notifyAll();
					break;
				}
				else
				{
					try
					{
						System.out.println(Thread.currentThread().getName()
								+ Defination.IN_WAIT);
						_producedData.wait();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
			if(!isEmpty())
			{
				if(!_producedData.isEmpty())
				{
					popNumber = _producedData.remove(0);
					System.out.println(Thread.currentThread().getName()
							+ Defination.CONSUMED + popNumber);
				}
				_producedData.notifyAll();
			}
		}
		return popNumber;
	}
}