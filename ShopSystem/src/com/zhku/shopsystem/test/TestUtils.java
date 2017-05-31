package com.zhku.shopsystem.test;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;

import org.junit.Test;

import com.zhku.shopsystem.utils.FileUtils;
import com.zhku.shopsystem.utils.MD5Utils;
import com.zhku.shopsystem.utils.MailUtils;
import com.zhku.shopsystem.utils.TimeUtils;
import com.zhku.shopsystem.utils.UUIDUtils;

public class TestUtils {
	
	@Test
	public void testUUIDUtils(){
		
		String code=UUIDUtils.getUUID();
		System.out.println(code);
	}
	
	@Test
	public void testMailUtils(){
		try {
			MailUtils.sendMail("359270069@qq.com", "3e488a99819d404cadace7090cc9cfae","林良劲");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMailUtils_getEmailUrl(){
		String url=MailUtils.getEmailUrl("359270069@qq.com");
		
		System.out.println(url);
	}	
	
	@Test
	public void testMap(){
		HashMap<Integer,String> hashMap=new LinkedHashMap<Integer,String>();
		
		hashMap.put(2, "蔡佳欣");
		hashMap.put(1, "林良劲");
		hashMap.put(3, "黄晓明");
		hashMap.put(4, "baby");
		
		for(Map.Entry<Integer,String> entry:hashMap.entrySet()){
			System.out.println(entry);
		}
		
		
	}
	
	@Test 
	public void testFileUtilsGetContentType(){
		
		System.out.println(FileUtils.getContentType("11.ico"));
		
	}
	
	@Test
	public void testTimeUitls(){
		
		//System.out.println(TimeUtils.getTimeMsg(60*2000));
		//1495768428516  2017/5/26  11:14
		System.out.println(TimeUtils.getSurplusTimeMsg(new Date(1495768428516l-3600*1000-53*60*10000)));
		
		
	}
	
	@Test
	public void testMd5Utils(){
		System.out.println(MD5Utils.md5("admin"));
	}
	
}
