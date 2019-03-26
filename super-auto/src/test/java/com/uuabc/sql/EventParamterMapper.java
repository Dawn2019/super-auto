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
						"VALUES ('"+parameterName+"', '"+Value+"', '"+UserUtil.StringTenTime()+"', '"+nowTime+"', ' ');";
				rs = stmt.executeUpdate(sql);
				JdbcUtil.close(conn, stmt, rs1);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			return null;
		}
		/**
		 * @author Dawn
		 * 查询参数化的值
		 */
		
		public static info getValueByType(String Value) {
			info info = new info();
			Connection conn;
			Statement stmt;
			ResultSet rs;
			try {
				conn = JdbcUtil.getConnection();
				stmt = conn.createStatement();
				String sql = "select parameterValue from `auto_test`.`ui_event_paramter` where parameterName='"+Value+"' order by id desc limit 1";
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