package com.zhku.shopsystem.utils;

import java.util.UUID;

public class UUIDUtils {
	
	public static String getUUID(){
	
		return UUID.randomUUID().toString().replace("-", "");
	}
	
}
