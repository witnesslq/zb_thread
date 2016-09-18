package zb_thread.exercise;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class Test3 extends Thread{
		
		private TestDo testDo;
		private String key;
		private String value;
		
		public Test3(String key,String key2,String value){
			this.testDo = TestDo.getInstance();
			/*常量"1"和"1"是同一个对象，下面这行代码就是要用"1"+""的方式产生新的对象，
			以实现内容没有改变，仍然相等（都还为"1"），但对象却不再是同一个的效果*/
			this.key = key;
			this.value = value;
		}


		public static void main(String[] args) throws InterruptedException{
			Test3 a = new Test3("1","","1");
			Test3 b = new Test3("1","","2");
			Test3 c = new Test3("3","","3");
			Test3 d = new Test3("4","","4");
			System.out.println("begin:"+(System.currentTimeMillis()/1000));
			a.start();
			b.start();
			c.start();
			d.start();

		}
		
		public void run(){
			testDo.doSome(key, value);
		}
	}


class TestDo {

	private TestDo() {}
	private static TestDo _instance = new TestDo();	
	public static TestDo getInstance() {
		return _instance;
	}
	//private List<Object> list = new ArrayList<Object>();
	private CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<Object>();
	public void doSome(Object key, String value) {
		Object o = key;
		if(!list.contains(o)){
			list.add(o);
		}else{
			Iterator<Object> iterator = list.iterator();
			while (iterator.hasNext()) {
				Object oo = iterator.next();
				if(oo.equals(o)){
					o = oo;
				}
			}
		}
		synchronized (o)
		// 以大括号内的是需要局部同步的代码，不能改动!
		{
			try {
				Thread.sleep(1000);
				System.out.println(key+":"+value + ":"
						+ (System.currentTimeMillis() / 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

