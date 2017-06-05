package cn.netkiller.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Yesterday {

	public Yesterday() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Yesterday yesterday = new Yesterday();
		System.out.println(yesterday.yesterday());
		System.out.println(yesterday.getYesterday("00:00:00"));
		System.out.println(yesterday.getYesterday("23:59:59"));
	}
	private Date yesterday() {
	    final Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -1);
	    return cal.getTime();
	}

	private Date getYesterday(String time) {
	        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd "+time);
	        
	        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Date date = null;
	        try {
	            date = fmt.parse(dateFormat.format(yesterday()));
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        return date;
	}
}
