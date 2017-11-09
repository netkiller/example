package cn.netkiller.example;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class TimeZoneTest {

	public TimeZoneTest() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		TimeZone timeZone = TimeZone.getTimeZone("Asia/Harbin");
		
		Date date = new Date();       
		Timestamp timestamp = new Timestamp(date.getTime());
		
		System.out.println(timestamp);
		
		timestamp.setHours(timestamp.getHours()+8);
		System.out.println(timestamp);
		
		
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		System.out.println(simpleDateFormat.format(date));
		
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Harbin"));
		System.out.println(simpleDateFormat.format(date));
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.setTimeZone(timeZone);
		System.out.println(simpleDateFormat.format(calendar.getTime()));
	}

}
