package testMultiThreading;

import java.util.ArrayList;
import java.util.List;
import operator.Consumer;
import operator.Producer;
import buffer.Buffer;
import utility.Defination;
import utility.Utility;

/**
 * Class Invoker
 * @author Vinayak
 *
 */
public class Invoker
{

	/**
	 * Starting point of program.
	 * @param args - command line arguments.
	 */
	public static void main(String[] args)
	{
		Invoker invokObject = new Invoker();
		invokObject.takeInput();
	}

	/**
	 * To take input from user.
	 * 
	 */
	private void takeInput()
	{
		int sizeOfBuffer ;
		int maxLimitOfProd ;
		int consumerCount ;
		System.out.print(Defination.ENTER_PROD_LIMIT);
		maxLimitOfProd = Utility.getIntegerInput();
		System.out.print(Defination.BUF_SIZE);
		sizeOfBuffer = Utility.getIntegerInput();
		System.out.print(Defination.CONSUMER_COUNT);
		consumerCount = Utility.getIntegerInput();
		System.out.println(Defination.HOR_LINE);
		System.out.println(Defination.START_MSG);
		System.out.println(Defination.HOR_LINE);
		begin(sizeOfBuffer, maxLimitOfProd, consumerCount);
	}

	/**
	 * 
	 * TO start execution.
	 * @param sizeOfBuffer - Buffer size.
	 * @param maxLimitOfProd - Limit of production.
	 * @param consumerCount - Count of consumer.
	 * 
	 */
	public void begin(int sizeOfBuffer ,int maxLimitOfProd, int consumerCount)
	{
		Buffer bufObject = new Buffer(sizeOfBuffer);	
		Producer producerObj = new Producer(maxLimitOfProd, bufObject);
		ArrayList<Consumer> aryConsumer = new ArrayList<Consumer>(0);
		Consumer consObj = null;
		for(int i = 0; i < consumerCount; i++)
		{
			consObj = new Consumer(bufObject);
			aryConsumer.add(consObj);
		}
		execute(producerObj, aryConsumer);
		display(aryConsumer, producerObj);
	}

	/**
	 * To execute Threads.
	 * @param producerObj - object of Producer
	 * @param aryConsumer - object of ArrayList
	 */
	private void execute(Producer producerObj, ArrayList<Consumer> aryConsumer)
	{
		final List<Thread> lstThread = new ArrayList<Thread>(0);
		final Thread producerThread = new Thread(producerObj, "Producer");
		lstThread.add(producerThread);
		producerThread.start();
		Thread consumerThread = null;
		int i = 0;
		for(final Consumer objConsumer : aryConsumer)
		{
			consumerThread = new Thread(objConsumer, Defination.CONSUMER
					+ (i + 1));
			i++;
			lstThread.add(consumerThread);
			consumerThread.start();
		}

		for(final Thread objThread : lstThread)
		{
			try
			{
				objThread.join();
			}
			catch(InterruptedException ex)
			{
			}
		}
	}

	/**
	 * To display details of data.
	 * @param aryConsumer - arrayList of consumer.
	 * @param producer - object of producer.
	 */
	public void  display(ArrayList<Consumer> aryConsumer , Producer producer)
	{
		System.out.println(Defination.HOR_LINE);
		int totConsume =0 ;
		for(int i = 0; i < aryConsumer.size() ; i++)
		{
			totConsume += aryConsumer.get(i)._totalConsumeSum ;
			System.out.println(Defination.INDI_CONSUMPTION +(i+1)+  Defination.IS_MSG +(aryConsumer.get(i)._totalConsumeSum));
		}
		System.out.println(Defination.HOR_LINE);
		System.out.println(Defination.TOTAL_PRODUCTION + producer._totProduction);
		System.out.println(Defination.TOTAL_CONSUMPTION  + totConsume);
		System.out.println(Defination.HOR_LINE);
	}	
}