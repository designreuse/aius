package com.spring.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UConnectionMaker implements ConnectionMaker{
	@Override
	public Connection makeConnection()  {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://127.0.0.1:3306/bbs?characterEncoding=utf8";
		String id = "root";
		String pass = "dlqpsxm03";

		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, id, pass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
}
