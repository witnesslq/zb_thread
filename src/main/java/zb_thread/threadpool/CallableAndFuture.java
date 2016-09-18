package zb_thread.threadpool;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 * Title:CallableAndFuture
 * Description:线程运行结果
 * @author    zwb
 * @date      2016年9月18日 下午12:18:00
 *
 */
public class CallableAndFuture {
	
	//在线程池基础上使用
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newSingleThreadExecutor();//单一线程池
		//使用executorService.submit方法传入callable接口重写call方法，返回future对象
		Future<String> future = executorService.submit(new Callable<String>() {//T为返回类型
			@Override
			public String call() throws Exception {
				return "hello";
			}
		});
		try {
			System.out.println(future.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		//提交一组callable
		//哪个线程执行完毕就返回此线程结果
		//ExecutorCompletionService
		ExecutorService threadPool2 = Executors.newFixedThreadPool(10);
		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool2);
		for(int i=1;i<=10;i++){
			final int seq = i;
			completionService.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					Thread.sleep(new Random().nextInt(5000));
					return seq;
				}
				
			});
		}
		for (int i = 1; i <= 10; i++) {
			try {
				System.out.println(completionService.take().get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
}
