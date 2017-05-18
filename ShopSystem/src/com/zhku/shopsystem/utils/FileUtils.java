package com.zhku.shopsystem.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

	
	public static String getContentType(String pathUri){
		Path path=Paths.get(pathUri);
		String contentType=null;
		try {
			contentType=Files.probeContentType(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contentType;
	}
	
}
