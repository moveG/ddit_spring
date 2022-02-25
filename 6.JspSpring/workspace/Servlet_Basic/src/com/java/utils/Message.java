package com.java.utils;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import org.apache.ibatis.io.Resources;

public class Message {
	//싱글톤 패턴
	private static Message instance = new Message();
	private Message() {}
	public static Message getInstance() {
		return instance;
	}
	
	private static Properties properties = new Properties();
	
	public static void initMessage(String propertiesURL) throws IOException{
		Reader reader = Resources.getResourceAsReader(propertiesURL);
		properties.load(reader);
	}
	
	public String getMessage(String key) {
		String message = properties.getProperty(key);
		return message;
	}
}
