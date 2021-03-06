package main_dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConn {
		String url ="jdbc:oracle:thin:@127.0.0.1:1521";
		String user ="scott";
		String pass = "tiger";
		Connection conn;
		PreparedStatement pstmt;
		ResultSet rs;
		Statement stmt;
		
		public DBConn() {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				conn = DriverManager.getConnection(url, user, pass);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/** PreparedStatement 생성 **/
		public void getPreparedStatement(String sql) {
			try {
				pstmt = conn.prepareStatement(sql);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/** Statement 생성 **/
		public void getStatement() {
			try {
				stmt = conn.createStatement();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/** 종료 **/
		public void close() {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
