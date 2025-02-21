package practice.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateScript
{
	public static void main(String[] args) {
		
	
			Date dateobj=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String date=sdf.format(dateobj);
			System.out.println(date);
			
		
		//Required Date
		
			//Date dateobj=new Date();
			//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			//String date=sdf.format(dateobj);
			Calendar cal=sdf.getCalendar();
			cal.add(Calendar.DAY_OF_MONTH, 30);
			String reqDate=sdf.format(cal.getTime());
			System.out.println(reqDate);
			
		}
	}

