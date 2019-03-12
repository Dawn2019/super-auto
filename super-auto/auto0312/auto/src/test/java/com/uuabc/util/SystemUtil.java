package com.uuabc.util;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class SystemUtil {
	private static String url_home = null;
	private static String url_admin = null;
	private static String driver = null;
	private static String Add = null;
	
	public static Map getSystem () {
		try {
			Map<String,String> map =null;
			Properties props = new Properties();
			InputStream in = SystemUtil.class.getClassLoader().getResourceAsStream("System.properties");
			props.load(in);
			url_admin = props.getProperty("url_admin");
			url_home = props.getProperty("url_home");
			driver = props.getProperty("driver");
			Add = props.getProperty("Add");
			map.put("url_home", url_home);
			map.put("url_admin", url_admin);
			map.put("driver", driver);
			map.put("Add", Add);
			return map;
		}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new RuntimeException(e);
	}
	}
}
