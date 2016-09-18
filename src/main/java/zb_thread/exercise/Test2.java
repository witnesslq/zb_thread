package zb_thread.exercise;

import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * Title:Test2
 * Description:
 * @author    zwb
 * @date      2016年9月18日 下午5:22:51
 *
 */
public class Test2 {
	
		//锁也可以
		public static void main(String[] args) {
			final Semaphore semaphore = new Semaphore(1);//信号灯
			final Lock lock = new ReentrantLock();
			final SynchronousQueue<String> strinSynchronousQueue = new SynchronousQueue<String>();//必须等待另一个线程进行移除操作，才put上去
			
			for(int i=1 ;i<=10;i++){
				new Thread(new Runnable() {
					@Override
					public void run() {
						//
						/*try {
							lock.lock();
							//semaphore.acquire();
							String output = TestDo.doSome(strinSynchronousQueue.take());
							System.out.println(Thread.currentThread().getName()+ ":" + output);
							//semaphore.release();
							lock.unlock();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}*/
						
					}
				}).start();
			}
			
			System.out.println("begin:"+(System.currentTimeMillis()/1000));
			for(int i=0;i<10;i++){  //这行不能改动
				String input = i+"";  //这行不能改动
				try {
					strinSynchronousQueue.put(input);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	//不能改动此TestDo类
	/*class TestDo {
		public static String doSome(String input){
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String output = input + ":"+ (System.currentTimeMillis() / 1000);
			return output;
		}
	}*/
