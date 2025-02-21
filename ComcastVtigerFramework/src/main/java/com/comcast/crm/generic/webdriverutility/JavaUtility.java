package com.comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility
{
	//Random Number
	
	public int getRandomNumber()
	{
		Random ranDom=new Random();
		int randomNumber=ranDom.nextInt(5000);
		return randomNumber;
		
	}
	
	//current date
	
	public String getSystemDateYYYYMMDD()
	{
		Date dateobj=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(dateobj);
		return date;
	}
	
	//Required Date
	
	public String getRequiredDateYYYYMMDD(int days)
	{
		Date dateobj=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(dateobj);
		Calendar cal=sdf.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String reqDate=sdf.format(cal.getTime());
		return reqDate;
	}

}
