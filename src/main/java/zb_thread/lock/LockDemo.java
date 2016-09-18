package zb_thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



/**
 * 
 * Title:LockDemo
 * Description:lock 锁 ，替换synchronized
 * @author    zwb
 * @date      2016年9月18日 下午12:33:28
 *
 */
public class LockDemo {
	
	public static void main(String[] args) {
		final OutPuter outPuter = new OutPuter();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					outPuter.output1("zhangweibao");
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					outPuter.output1("doublezhang");
				}
			}
		}).start();
	}
	
	static class OutPuter{
		Lock lock = new ReentrantLock();
		//1、在lock替换synchronized(注意，是使用同一把锁)
		public void output1(String name){
			int len = name.length();
			lock.lock();
			for (int i = 0; i<len ;i++) {
				System.out.print(name.charAt(i));
			}
			System.out.println();
			lock.unlock();
		}			
	}
}
