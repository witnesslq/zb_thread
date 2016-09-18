package zb_thread.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * Title:ExchangerDemo
 * Description:两个线程交换
 * @author    zwb
 * @date      2016年9月18日 下午3:58:46
 *
 */
public class ExchangerDemo {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final Exchanger exchanger = new Exchanger();
		service.execute(new Runnable(){
			public void run() {
				try {				

					String data1 = "zxx";
					System.out.println("线程" + Thread.currentThread().getName() + 
					"正在把数据" + data1 +"换出去");
					Thread.sleep((long)(Math.random()*10000));
					String data2 = (String)exchanger.exchange(data1);
					System.out.println("线程" + Thread.currentThread().getName() + 
					"换回的数据为" + data2);
				}catch(Exception e){
					
				}
			}	
		});
		service.execute(new Runnable(){
			public void run() {
				try {				

					String data1 = "lhm";
					System.out.println("线程" + Thread.currentThread().getName() + 
					"正在把数据" + data1 +"换出去");
					Thread.sleep((long)(Math.random()*10000));					
					String data2 = (String)exchanger.exchange(data1);
					System.out.println("线程" + Thread.currentThread().getName() + 
					"换回的数据为" + data2);
				}catch(Exception e){
					
				}				
			}	
		});		
	}
}
