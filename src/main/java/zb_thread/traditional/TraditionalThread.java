package zb_thread.traditional;

/**
 * 
 * Title:TraditionalThread
 * Description:传统线程
 * @author    zwb
 * @date      2016年9月18日 上午9:57:05
 *
 */
public class TraditionalThread {
	public static void main(String[] args) {
		//使用方式：
		//方式1
		Thread thread1 = new Thread(){
			@Override
			public void run() {
				//do something
			}
		};
		thread1.start();
		
		//方式2(更符合面向对象编程思想)
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				//do something
			}
		});
		thread2.start();
		
		//当使用Thread构造方法的参数方式，与子类继承的方式同时使用，则线程最终执行哪个一个run方法？
		//会使用子类继承的方式的run方法，因为父类的run方法会判断runnable是否为空，如果不为空则使用runnable的run方法
		Thread thread3 = new Thread(new Runnable() {//钩子方法
			@Override
			public void run() {
				//do something
			}
		}){
			@Override
			public void run() {
				//do something
			}
		};
		thread3.start();
		
	}
}
