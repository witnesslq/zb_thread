package zb_thread.threadlocal;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 
 * Title:ThreadScopeShareData
 * Description:线程范围内共享数据（原理）
 * @author    zwb
 * @date      2016年9月18日 上午11:27:11
 *
 */
public class ThreadScopeShareData {
	private static int data = 0;
	private static Map<Thread, Integer> threadData = new HashMap<Thread, Integer>();
	
	//把数据与线程放在一个map里面，获取时候根据线程获取数据，达到线程范围内共享数据的需求
	public static void main(String[] args) {
		for(int i=0;i<2;i++){
			new Thread(new Runnable(){
				@Override
				public void run() {
					int data = new Random().nextInt();
					System.out.println(Thread.currentThread().getName() 
							+ " has put data :" + data);
					threadData.put(Thread.currentThread(), data);
					new A().get();
					new B().get();
				}
			}).start();
		}
	}
	
	static class A{
		public void get(){
			int data = threadData.get(Thread.currentThread());
			System.out.println("A from " + Thread.currentThread().getName() 
					+ " get data :" + data);
		}
	}
	
	static class B{
		public void get(){
			int data = threadData.get(Thread.currentThread());			
			System.out.println("B from " + Thread.currentThread().getName() 
					+ " get data :" + data);
		}		
	}
}
