package zb_thread.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 
 * Title:BlockingQueueDemo
 * Description:阻塞队列
 * @author    zwb
 * @date      2016年9月18日 下午4:10:20
 *
 */
public class BlockingQueueDemo {
	
	public static void main(String[] args) {
		final BlockingQueue queue = new ArrayBlockingQueue(3);
		for(int i=0;i<2;i++){
			new Thread(){
				public void run(){
					while(true){
						try {
							Thread.sleep((long)(Math.random()*1000));
							System.out.println(Thread.currentThread().getName() + "准备放数据!");							
							queue.put(1);
							System.out.println(Thread.currentThread().getName() + "已经放了数据，" + 							
										"队列目前有" + queue.size() + "个数据");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}
				}
				
			}.start();
		}
		
		//因为取数据,与打印，不能保持原子性，所以会出现，取了是取了，但是cpu跑到其他线程，导致没有打印
		new Thread(){
			public void run(){
				while(true){
					try {
						//将此处的睡眠时间分别改为100和1000，观察运行结果
						Thread.sleep(1000);
						System.out.println(Thread.currentThread().getName() + "准备取数据!");
						queue.take();
						System.out.println(Thread.currentThread().getName() + "已经取走数据，" + 							
								"队列目前有" + queue.size() + "个数据");					
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		}.start();			
	}
}
