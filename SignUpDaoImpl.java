package com.skk.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.skk.dao.SignUpDao;
import com.skk.pojo.SignUp;
import com.skk.utility.DBUtility;

public class SignUpDaoImpl implements SignUpDao{

	@Override
	public boolean addUser(SignUp s) {
		String addUserQuery;
		Connection conn;
		PreparedStatement ps;
		int row;
		addUserQuery = "insert into Users(uFname, uLname, uContactNo, uLocation, uEmailId, uPass) values(?,?,?,?,?,?)";
		
		try {
			conn = DBUtility.establishConnection();
			ps = conn.prepareStatement(addUserQuery);
			ps.setString(1, s.getUserFname());
			ps.setString(2, s.getUserLname());
			ps.setLong(3, s.getUserContactNo());
			ps.setString(4, s.getUserLocation());
			ps.setString(5, s.getUserEmailId());
			ps.setString(6, s.getUserPass());
			
			row = ps.executeUpdate();
			if(row>0)
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