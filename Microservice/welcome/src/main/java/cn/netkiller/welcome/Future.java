package cn.netkiller.welcome;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Future {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
			@Override
			public String call() throws Exception {
				String status = null;
				System.out.println(Thread.currentThread().getName() + ":" + "Send SMS ...");
				Thread.sleep(2000);
				System.out.println(Thread.currentThread().getName() + ":" + "Sent");
				status = "OK";
				return status;
			}
		});

//		开启了一个线程执行future的逻辑
		Thread thread = new Thread(futureTask);
		thread.start();

		// 主业务逻辑
		System.out.println(Thread.currentThread().getName() + ":" + "Begin");
		Thread.sleep(3000);
		System.out.println(Thread.currentThread().getName() + ":" + "End");

		String sent = futureTask.get();

		System.out.println(Thread.currentThread().getName() + ":" + "Status：" + sent + " done!");
	}

}
