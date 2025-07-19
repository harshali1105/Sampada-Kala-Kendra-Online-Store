package com.skk.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.skk.dao.SignInDao;
import com.skk.utility.DBUtility;

public class SignInDaoImpl implements SignInDao{
	PreparedStatement ps;
	ResultSet rs;
	Connection conn;
	int row;
	String displayUserByEmailIdQuery;
	@Override
	public boolean getUserPasswordByEmail(String userEmailId, String userPass) {
		displayUserByEmailIdQuery = "select uId from Users where uEmailId = ? and uPass=?";
		
		try 
		{
			conn = DBUtility.establishConnection();
			ps=conn.prepareStatement(displayUserByEmailIdQuery);
			ps.setString(1, userEmailId);
			ps.setString(2, userPass);
			rs = ps.executeQuery();
			if(rs.next()) 
			{
				return true;
				
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

}
