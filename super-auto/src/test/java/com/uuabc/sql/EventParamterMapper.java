package com.uuabc.sql;

import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import com.uuabc.util.JdbcUtil;
import com.uuabc.util.UserUtil;
import com.uuabc.util.info;
public class EventParamterMapper {

		/**
		 * @author Dawn
		 * 更新要参数化的值
		 *
		 */
		
		public static info updateParameter(String parameterName,String Value,String nowTime) {
			info info = new info();
			Connection conn;
			Statement stmt;
			int rs;
			ResultSet rs1 = null;
			try {
				conn = JdbcUtil.getConnection();
				stmt = conn.createStatement();
				String sql = "INSERT INTO `auto_test`.`ui_event_paramter` (`parameterName`, `parameterValue`, `creat_time`, `update_time`,`description`) \r\n" + 
<<<<<<< HEAD:super-auto/src/test/java/com/uuabc/sql/EventParamterMapper.java
						"VALUES ('"+parameterName+"', '"+Value+"', '"+UserUtil.StringTenTime()+"', '"+nowTime+"', ' ');";
=======
						"VALUES ('TeacherEmail', '"+Value+"', '"+UserUtil.StringTenTime()+"', '"+nowTime+"', 'student phone');";
>>>>>>> 829ea08315c5829e72364b421c682dc3d8b0bbcc:super-auto/src/test/java/com/uuabc/sql/EventParamterMapper.java
				rs = stmt.executeUpdate(sql);
				JdbcUtil.close(conn, stmt, rs1);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			return null;
		}
<<<<<<< HEAD:super-auto/src/test/java/com/uuabc/sql/EventParamterMapper.java
		/**
		 * @author Dawn
		 * 查询参数化的值
=======
		
		
		/**
		 * 
		 * 新增一位学生时记录手机号
		 *
		 */
		
		public static info updatephone(String Value,String nowTime) {
			info info = new info();
			Connection conn;
			Statement stmt;
			int rs;
			ResultSet rs1 = null;
			try {
				conn = JdbcUtil.getConnection();
				stmt = conn.createStatement();
				String sql = "INSERT INTO `auto_test`.`ui_event_config` (`parameterName`, `parameterValue`, `creat_time`, `update_time`,`description`) \r\n" + 
						"VALUES ('StudentPhone', '"+Value+"', '"+UserUtil.StringTenTime()+"', '"+nowTime+"', 'teacher email');";
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
		 * 查询老师的邮箱/学生的手机号
>>>>>>> 829ea08315c5829e72364b421c682dc3d8b0bbcc:super-auto/src/test/java/com/uuabc/sql/EventParamterMapper.java
		 */
		
		public static info getValueByType(String Value) {
			info info = new info();
			Connection conn;
			Statement stmt;
			ResultSet rs;
			try {
				conn = JdbcUtil.getConnection();
				stmt = conn.createStatement();
<<<<<<< HEAD:super-auto/src/test/java/com/uuabc/sql/EventParamterMapper.java
				String sql = "select parameterValue from `auto_test`.`ui_event_paramter` where parameterName='"+Value+"' order by id desc limit 1";
=======
				String sql = "select parameterValue from ui_event_config where parameterName='"+Value+"' order by id desc limit 1";
>>>>>>> 829ea08315c5829e72364b421c682dc3d8b0bbcc:super-auto/src/test/java/com/uuabc/sql/EventParamterMapper.java
				rs = stmt.executeQuery(sql);
				if (rs!=null){
					while(rs.next()) {
					info.setParameterValue(rs.getString("parameterValue"));
					}
				}
				JdbcUtil.close(conn, stmt, rs);
				return info;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		
		
	}