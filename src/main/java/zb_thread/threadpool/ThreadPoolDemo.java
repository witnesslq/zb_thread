package zb_thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * 
 * Title:ThreadPoolDemo
 * Description:线程池
 * @author    zwb
 * @date      2016年9月18日 上午11:51:37
 *
 */
public class ThreadPoolDemo {
	//基于jdk中线程并发库java.util.concurrent.atomic
	public static void main(String[] args) {
		
		//固定线程池 
		ExecutorService executorService1 = Executors.newFixedThreadPool(3);
		executorService1.execute(new Runnable() {
			@Override
			public void run() {
				//do something
			}
		});
		
		//缓存线程池
		ExecutorService executorService2 = Executors.newCachedThreadPool();
		for (int i = 1; i <= 10; i++) {
			final int task = i;
			executorService2.execute(new Runnable() {
				@Override
				public void run() {
					for(int j = 1 ;j<=10;j++){
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName() + " loop of " + j + " for task " + task);
					}
				}
			});
		}
		System.out.println("all of tasks have committed!");
		executorService2.shutdown();//线程空闲就结束
		
		//定时器线程池
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
		scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println("fire in the hold");
			}
		},6, 2, TimeUnit.SECONDS);
	}
}
