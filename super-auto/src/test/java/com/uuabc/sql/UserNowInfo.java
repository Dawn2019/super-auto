package com.uuabc.sql;

import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import com.uuabc.util.JdbcUtil;
import com.uuabc.util.UserBase;
import com.uuabc.util.info;
public class UserNowInfo {

		/**
		 * 
		 * 新增一位老师时记录邮箱
		 *
		 */
		
		public static info updateEmail(String Value,String nowTime) {
			info info = new info();
			Connection conn;
			Statement stmt;
			int rs;
			ResultSet rs1 = null;
			try {
				conn = JdbcUtil.getConnection();
				stmt = conn.createStatement();
				String sql = "INSERT INTO `auto_test`.`ui_event_config` (`parameterName`, `parameterValues`, `creat_time`, `update_time`,`description`) \r\n" + 
						"VALUES ('TeacherEmail', '"+Value+"', '"+UserBase.StringTenTime()+"', '"+nowTime+"', 'teacher email');";
				rs = stmt.executeUpdate(sql);
				JdbcUtil.close(conn, stmt, rs1);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			return null;
		}
		
		/**
		 * 
		 * 查询老师的邮箱
		 */
		
		public static info getValueByType(String parameterName) {
			info info = new info();
			Connection conn;
			Statement stmt;
			ResultSet rs;
			try {
				conn = JdbcUtil.getConnection();
				stmt = conn.createStatement();
				String sql = "select parameterValue from ui_event_config where parameterName='"+parameterName+"'order by id desc";
				rs = stmt.executeQuery(sql);
				if (rs!=null){
				while (rs.next()) {
					info.setParameterValue(rs.getString("parameterValue"));
				}
				return info;
				}
				JdbcUtil.close(conn, stmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			return null;
		}
		
		
	}