package cn.netkiller.example;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateCompare {

	public DateCompare() {
		// TODO Auto-generated constructor stub
	}

	public void fun1() throws InterruptedException {
		Date d1 = new Date();
		Thread.sleep(5000);
		Date d2 = new Date();
		if (d1.before(d2)) {
			System.out.println(String.format("%s < %s", d1.toString(), d2.toString()));
		} else {
			System.out.println(String.format("%s > %s", d1.toString(), d2.toString()));
		}
		if (d2.after(d1)) {
			System.out.println(String.format("%s > %s", d2.toString(), d1.toString()));
		}

		System.out.println(String.format("%s : %s => %d", d2.toString(), d1.toString(), d1.compareTo(d2)));
		System.out.println(String.format("%s : %s => %d", d1.toString(), d2.toString(), d2.compareTo(d1)));
	}

	public void fun2() throws InterruptedException {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date date1 = new Date();
		Date date2 = new Date();
		String s1 = dateFormat.format(date1);
		String s2 = dateFormat.format(date2);
		System.out.println(String.format("%s : %s => %d", s1, s2, s1.compareTo(s2)));

		date1 = new Date();
		Thread.sleep(5000);
		date2 = new Date();
		s1 = dateFormat.format(date1);
		s2 = dateFormat.format(date2);
		System.out.println(String.format("%s : %s => %d", s1, s2, s1.compareTo(s2)));
		System.out.println(String.format("%s : %s => %d", s2, s1, s2.compareTo(s1)));
		System.out.println();

	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		DateCompare dateCompare = new DateCompare();
		dateCompare.fun1();
		System.out.println();
		dateCompare.fun2();

	}

}
