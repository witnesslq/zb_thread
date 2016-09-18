package zb_thread.threadlocal;

import java.util.Random;

/**
 * 
 * Title:ThreadLocalDemo
 * Description:线程范围内共享数据，ThreadLocal 的使用
 * @author    zwb
 * @date      2016年9月18日 上午11:06:40
 *
 */
public class ThreadLocalDemo {
	//简单类型
	private static ThreadLocal<Integer> dataInt = new ThreadLocal<Integer>();
	//包装类型与简单类型是一样的，只要线程使用同一成员变量就行
	private static ThreadLocal<MyThreadScopeData> dataPo = new ThreadLocal<MyThreadScopeData>();
	
	public static void main(String[] args) {
		//两个线程共享一份数据
		for (int i = 1; i <= 2; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					//简单类型
					int data = new Random().nextInt();
					System.out.println(Thread.currentThread().getName() + " has put data:" + data);
					dataInt.set(data);
					//包装类型（声明成成员变量与简单类型用法一样，不做演示）
					MyThreadScopeData myThreadScopeData = MyThreadScopeData.getInstance();
					myThreadScopeData.setName("doublezhang" + data);
					myThreadScopeData.setAge(data);
					System.out.println(Thread.currentThread().getName() + " has put data:" +  myThreadScopeData.getName() + "|" + myThreadScopeData.getAge());
					
					new A().get();
					new B().get();
				}
			}).start();
		}
	}
	
	static class A{
		public void get(){
			int data = dataInt.get();
			System.out.println("A from " + Thread.currentThread().getName() + " get data :" + data);
			MyThreadScopeData myThreadScopeData = MyThreadScopeData.getInstance();
			System.out.println("A from " + Thread.currentThread().getName() + " get data :" + myThreadScopeData.getName() + "|" + myThreadScopeData.getAge());
			
		}
	}
	
	static class B{
		public void get(){
			int data = dataInt.get();	
			System.out.println("B from " + Thread.currentThread().getName() + " get data :" + data);
			MyThreadScopeData myThreadScopeData = MyThreadScopeData.getInstance();
			System.out.println("B from " + Thread.currentThread().getName() + " get data :" + myThreadScopeData.getName() + "|" + myThreadScopeData.getAge());
		}		
	}
}

//外部类
//使用单利模式，一种把成员变量在本内实例化（饥寒模式，饱含模式）
class MyThreadScopeData{
	private MyThreadScopeData(){}//把无参构造方法私有化

	//private static MyThreadScopeData instance = new MyThreadScopeData();//饱含模式
	//private static MyThreadScopeData instance = null;//饥寒模式
	
	private static ThreadLocal<MyThreadScopeData> map = new ThreadLocal<MyThreadScopeData>();
	
	public static /*synchronized*/ MyThreadScopeData getInstance(){//不需要互斥也行
		MyThreadScopeData instance = map.get();
		if(instance == null){
			instance = new MyThreadScopeData();
			map.set(instance);
		}
		return instance;
	}
	
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
