package zb_thread.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 
 * Title:SemaphoreDemo
 * Description:信号灯，与互斥类似，控制同时访问线程个数
 * 
 * 但是锁只能谁谁上的锁就谁可以解锁，而信号灯可以解别人的锁,差不多这个意思
 * @author    zwb
 * @date      2016年9月18日 下午2:52:25
 *
 */
public class SemaphoreDemo {
	
	/**
	 * 声明信号灯Semaphore semaphore = new Semaphore(3);
	 * 拿灯：semaphore.acquire();
	 * 弃灯：semaphore.release();
	 */
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		final Semaphore semaphore = new Semaphore(3);//3个信号灯
		for (int i = 1; i <= 10; i++) {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						semaphore.acquire();//拿灯
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("线程" + Thread.currentThread().getName() + 
							"进入，当前已有" + (3-semaphore.availablePermits()) + "个并发");//avilablePermits()还剩几个灯
					try {
						Thread.sleep((long)(Math.random()*10000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("线程" + Thread.currentThread().getName() + 
							"即将离开");					
					semaphore.release();
					//下面代码有时候执行不准确，因为其没有和上面的代码合成原子单元
					System.out.println("线程" + Thread.currentThread().getName() + 
							"已离开，当前已有" + (3-semaphore.availablePermits()) + "个并发");			
				}
			};
			executorService.execute(runnable);
		}
	}
}
