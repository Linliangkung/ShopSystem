package com.zhku.shopsystem.utils;

import java.util.Date;

import org.aspectj.weaver.reflect.IReflectionWorld;

public class TimeUtils {
	
	/**
	 * 根据传入的时间:long型,返回时长
	 * @param time 时间
	 * @return 时长
	 */
	public static String getTimeMsg(long time){
		if(time<0){
			return "0分钟";
		}
		long hour =time/3600000;
		
		long minuteLong = time%3600000;
		
		long minute=minuteLong/60000;
		
		if(hour!=0&&minute!=0){
			return hour+"小时"+minute+"分钟"; 
		}else if(hour==0&&minute!=0){
			return minute+"分钟";
		}else if(hour!=0&&minute==0){
			return hour+"小时";
		}else{
			return "1分钟";
		}
	}
	
	
	public static String getSurplusTimeMsg(Date targetDate){
		
		long targetTime=targetDate.getTime();
		long surplusTime=3600*2*1000-(new Date().getTime()-targetTime);
		
		return getTimeMsg(surplusTime);
		
		
	}
}
