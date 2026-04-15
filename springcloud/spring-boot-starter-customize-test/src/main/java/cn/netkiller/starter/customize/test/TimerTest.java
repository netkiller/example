package cn.netkiller.starter.customize.test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				System.out.println("task  run:" + new Date());
			}

		};

		Timer timer = new Timer();

		// 每3秒执行一次
		timer.schedule(timerTask, 10, 3000);
	}

}
