package com.uuabc.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.management.RuntimeErrorException;

import com.uuabc.util.JdbcUtil;
import com.uuabc.util.info;

public class EventMapper {
	
	
	public static info getTypeByCode(String code) {
		info info = new info();
		Connection conn;
		Statement stmt;
		ResultSet rs;
		
		try {
			conn =JdbcUtil.getConnection();
			stmt =conn.createStatement();
			String sql= "select type,webelement,event,value from ui_event where code='"+code+"'";
			rs =stmt.executeQuery(sql);
			if(rs !=null) {
				while(rs.next()) {
					info.setType(rs.getString("type"));
					info.setWebelement(rs.getString("webelement"));
					info.setValue(rs.getString("value"));
					info.setEvent(rs.getString("event"));
				}
			}
			
			JdbcUtil.close(conn, stmt, rs);
			return info;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}
