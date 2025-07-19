package com.skk.utility;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtility {
	public static Connection establishConnection() throws SQLException, ClassNotFoundException
	{
		String url,user,password;
		url = "jdbc:mysql://localhost:3306/Sampada_Kala_Kendra";
		user = "root";
		password = "root";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url,user,password);
		return conn;
		
	}
}
