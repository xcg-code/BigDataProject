package bigdata.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Util {
	/**
	 * 从日志中解析出日期信息
	 */
	public static String format(String log,String pattern){
		String[] arr=log.split(" ");
		String datePart=arr[3];
		SimpleDateFormat sdf=new SimpleDateFormat("[dd/MMM/yyyy:HH:mm:ss",Locale.US);
		Date date;
		try {
			date = sdf.parse(datePart);
			SimpleDateFormat sdf_china=new SimpleDateFormat(pattern);
			return sdf_china.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public static String getDate(String log){
		return format(log,"yyyy/MM/dd");
	}
	public static String getTime(String log){
		return format(log,"HH:mm:ss");
	}
	public static String  getFullTime(String log){
		return format(log,"yyyy/MM/dd HH:mm:ss");
	}

}
