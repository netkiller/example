package cn.netkiller.starter.customize.test;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

		// 参数：执行命令，初始执行的延时时间，任务执行间隔，间隔时间单位
		service.scheduleAtFixedRate(() -> System.out.println("ScheduledExecutorService " + new Date()), 0, 3, TimeUnit.SECONDS);

	}

}
