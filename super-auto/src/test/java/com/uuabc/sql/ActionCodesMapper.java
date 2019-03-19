package com.uuabc.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.management.RuntimeErrorException;

import com.uuabc.util.JdbcUtil;
import com.uuabc.util.info;

public class ActionCodesMapper {
	public static info getActionCodes(String aaa) {
		info info = new info();
		Connection conn;
		Statement stmt;
		ResultSet rs;
		try {
			conn = JdbcUtil.getConnection();
			stmt = conn.createStatement();
			String sql = "select codes from ui_event_actionCodes where action='"+aaa+"'";
			rs =stmt.executeQuery(sql);
			if(rs!=null) {
				while(rs.next()) {
					info.setType(rs.getString("codes"));
				}
				return info;
			}
			JdbcUtil.close(conn, stmt, rs);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return null;
	}
}
