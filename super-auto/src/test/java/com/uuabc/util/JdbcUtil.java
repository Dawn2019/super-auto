package com.uuabc.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.management.RuntimeErrorException;

public class JdbcUtil {
	private static String url = null;
	private static String username = null;
	private static String password = null;
	private static String driverclass = null;
	
	static {
		try {
			Properties props =new Properties();
			//读取配置文件
			InputStream in = JdbcUtil.class.getClassLoader().getResourceAsStream("dbconfig.properties");
			props.load(in);
			url = props.getProperty(url);
			username = props.getProperty("username");
			password =props.getProperty("password");
			driverclass =props.getProperty("driverClassName");
			Class.forName(driverclass);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static Connection getConnection() {
		//连接数据库方法
		try {
			return DriverManager.getConnection(url,username,password);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
		public static void close(Connection conn,Statement stmt,ResultSet rs) {
		//关闭数据库
			if(rs != null) {
				try{
					rs.close();
				}catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
			if(stmt != null) {
				try{
					stmt.close();
				}catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
			if(conn != null) {
				try{
					conn.close();
				}catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		
	}
	
}
