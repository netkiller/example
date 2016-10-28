package cn.netkiller.example;

import java.util.Calendar;
import java.util.Date;

public class DateTest {

	public DateTest() {
		// TODO Auto-generated constructor stub
	}
	public void test(){
		String cacheKey = String.format("%s:%s", this.getClass().getName(), Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println(cacheKey);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DateTest s = new DateTest();
		s.test();

		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH )+1;
		
		System.out.println(year + "年 " + month + "月");
		
		Date date = new Date();
		System.out.println(date);
	}

}
