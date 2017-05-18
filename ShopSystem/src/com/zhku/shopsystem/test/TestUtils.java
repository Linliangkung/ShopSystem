package com.zhku.shopsystem.test;

import javax.mail.MessagingException;

import org.junit.Test;

import com.zhku.shopsystem.utils.FileUtils;
import com.zhku.shopsystem.utils.MailUtils;
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
	public void testFileUtilsGetContentType(){
		
		System.out.println(FileUtils.getContentType("11.ico"));
		
	}
	
}
