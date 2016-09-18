package zb_thread.lock;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 
 * Title:ReadWriteLockDemo
 * Description:读写锁
 * @author    zwb
 * @date      2016年9月18日 下午1:38:14
 *
 */
public class ReadWriteLockDemo {
	public static void main(String[] args) {
		final Queue queue = new Queue();
		for (int i = 1; i <= 3; i++) {//开始3个读写线程
			new Thread(new Runnable() {
				@Override
				public void run() {//读
					while(true){
						queue.get();
					}
				}
			}).start();
			new Thread(new Runnable() {
				@Override
				public void run() {//写
					while(true){
						queue.put(new Random().nextInt(10000));
					}
				}
			}).start();
			
		}
	}
}

//读能读，读不能写，写不能读，写不能写
class Queue{
	private Object data = null;//存放数据
	
	ReadWriteLock rwl = new ReentrantReadWriteLock();//同一个读写锁对象
	
	//读数据
	public void get(){
		rwl.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " be ready to read data!");
			Thread.sleep((long)(Math.random()*1000));
			System.out.println(Thread.currentThread().getName() + " have read data :" + data);	
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			rwl.readLock().unlock();
		}	
	}
	//写数据
	public void put(Object data){
		rwl.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " be ready to write data!");
			Thread.sleep((long)(Math.random()*1000));
			this.data = data;	
			System.out.println(Thread.currentThread().getName() + " have write data :" + data);	
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			rwl.writeLock().unlock();;
		}
	}
}
