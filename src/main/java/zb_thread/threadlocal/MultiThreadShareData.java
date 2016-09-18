package zb_thread.threadlocal;



/**
 * 
 * Title:MultiThreadShareData
 * Description:多线程共享数据练习
 * @author    zwb
 * @date      2016年9月18日 上午11:36:53
 *
 */
public class MultiThreadShareData {
	//设计4个线程，其中两个线程每次对j增加1，另外两个线程对j每次减少1
	public static void main(String[] args) {
		final ShareData shareData = new ShareData();//如果代码项目，放在同一个runnable对象就行了
		
		new Thread(new MyRunnable1(shareData)).start();
		new Thread(new MyRunnable2(shareData)).start();
	
		//把这个对象传到个runnable对象中
		new Thread(new Runnable() {
			@Override
			public void run() {
				shareData.decrement();
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				shareData.increment();
			}
		}).start();
	}
	
}

//两个runnable
class MyRunnable1 implements Runnable{
	private ShareData shareData;
	
	public MyRunnable1(ShareData shareData) {
		this.shareData = shareData;
	}
	@Override
	public void run() {
		shareData.decrement();
	}
}

class MyRunnable2 implements Runnable{
	private ShareData shareData;
	
	public MyRunnable2(ShareData shareData) {
		this.shareData = shareData;
	}
	
	@Override
	public void run() {
		shareData.increment();
	}
}

class ShareData{
	private int j = 0;
	
	//j加1
	public synchronized void increment(){
		j++;
		System.out.println(j);
	}
	//j减1
	public synchronized void decrement(){
		j--;
		System.out.println(j);
	}
}