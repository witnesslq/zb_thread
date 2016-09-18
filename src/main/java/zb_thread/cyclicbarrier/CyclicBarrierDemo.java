package zb_thread.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * Title:CyclicBarrierDemo
 * Description:路障
 * 等待线程到达一个集合点则继续出发
 * @author    zwb
 * @date      2016年9月18日 下午3:13:28
 *
 */
public class CyclicBarrierDemo {
	//如集体旅游
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		final  CyclicBarrier cb = new CyclicBarrier(3);//约定要3个线程到达
		for (int i = 1; i <= 3; i++) {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep((long)(Math.random()*10000));	
						System.out.println("线程" + Thread.currentThread().getName() + 
								"即将到达集合地点1，当前已有" + (cb.getNumberWaiting()+1) + "个已经到达，" + (cb.getNumberWaiting()==2?"都到齐了，继续走啊":"正在等候"));						
						cb.await();
						
						Thread.sleep((long)(Math.random()*10000));	
						System.out.println("线程" + Thread.currentThread().getName() + 
								"即将到达集合地点2，当前已有" + (cb.getNumberWaiting()+1) + "个已经到达，" + (cb.getNumberWaiting()==2?"都到齐了，继续走啊":"正在等候"));
						cb.await();	
						Thread.sleep((long)(Math.random()*10000));	
						System.out.println("线程" + Thread.currentThread().getName() + 
								"即将到达集合地点3，当前已有" + (cb.getNumberWaiting() + 1) + "个已经到达，" + (cb.getNumberWaiting()==2?"都到齐了，继续走啊":"正在等候"));						
						cb.await();						
					} catch (Exception e) {
						e.printStackTrace();
					}			
				}
			};
			executorService.execute(runnable);
		}
		executorService.shutdown();
	}
}
