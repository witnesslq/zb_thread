package zb_thread.traditional;

/**
 * 
 * Title:TraditionalThreadExercise
 * Description:练习
 * 子线程循环10次，接着主线程循环100，接着又回到子线程循环10次，接着再会到主线程循环100次，如此循环50次
 * @author    zwb
 * @date      2016年9月18日 上午10:47:32
 *
 */
public class TraditionalThreadExercise {
	
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
					business.sub(i);
				}
			}
		}).start();
		
		for(int i = 1; i <=50; i++){
			business.main(i);
		}
	}
	
	//wait()和notify()必须在synchronized关键字中
	class Business{
		private boolean bShouldSub = true;
		//子线程循环10次
		public synchronized void sub(int i){
			while(!bShouldSub){
				try {
					this.wait();//等待
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for(int j = 1; j<= 10; j++){
				System.out.println("sub thread sequece of " + j + ",loop of " + i);
			}
			bShouldSub = false;
			this.notify();//唤醒
		}
		//主线程循环100次
		public synchronized void main(int i){
			while(bShouldSub){
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for(int j = 1; j<= 100; j++){
				System.out.println("main thread sequece of " + j + ",loop of " + i);
			}
			bShouldSub = true;
			this.notify();
		}
	}
}
