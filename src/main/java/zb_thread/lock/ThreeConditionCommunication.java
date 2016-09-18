package zb_thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import zb_thread.traditional.TraditionalThreadExercise;

/**
 * 
 * Title:ThreeConditionCommunication
 * Description:使用condition 实现子线程循环10次，接着主线程循环100，接着又回到子线程循环10次，接着再会到主线程循环100次，如此循环50次
 * @author    zwb
 * @date      2016年9月18日 下午2:33:20
 *
 */
public class ThreeConditionCommunication {
	public static void main(String[] args) {
		new TraditionalThreadExercise().init();
	}
	
	public void init(){
		final Business business = new Business();
		new Thread(new Runnable() {
			@Override
			public void run() {
				//如此循环50次
				for (int i = 1; i <=50; i++) {
					business.sub2(i);
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				//如此循环50次
				for (int i = 1; i <=50; i++) {
					business.sub3(i);
				}
			}
		}).start();
		
		for(int i = 1; i <=50; i++){
			business.main(i);
		}
	}
	
	class Business{
		private Lock lock = new ReentrantLock();
		private Condition condition1 = lock.newCondition();
		private Condition condition2 = lock.newCondition();
		private Condition condition3 = lock.newCondition();
		
		private int bShouldSub = 1;
		//子线程循环10次
		public void sub2(int i){
			lock.lock();
			try{
				while(bShouldSub != 2){
					try {
						condition2.await();//自己等待，唤醒sub3
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for(int j = 1; j<= 10; j++){
					System.out.println("sub2 thread sequece of " + j + ",loop of " + i);
				}
				bShouldSub = 3;
				condition3.signal();
			}finally{
				lock.unlock();
			}
		}
		public void sub3(int i){
			lock.lock();
			try{
				while(bShouldSub != 3){
					try {
						condition3.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for(int j = 1; j<= 10; j++){
					System.out.println("sub3 thread sequece of " + j + ",loop of " + i);
				}
				bShouldSub = 1;
				condition1.signal();
			}finally{
				lock.unlock();
			}
		}
		//主线程循环100次
		public void main(int i){
			lock.lock();
			try{
				while(bShouldSub != 1){
					try {
						condition1.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for(int j = 1; j<= 100; j++){
					System.out.println("main thread sequece of " + j + ",loop of " + i);
				}
				bShouldSub = 2;
				condition2.signal();
			}finally{
				lock.unlock();
			}
		}
	}
}
