package com.skk.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.skk.dao.AdminDao;
import com.skk.utility.DBUtility;

public class AdminDaoImpl implements AdminDao{
	Connection conn;
	ResultSet rs;
	PreparedStatement ps;
	String validateAdminQuery;
	@Override
	public boolean validateAdmin(String adminPassword) {
		validateAdminQuery = "select * from admin where adminPassword=?";
		try {
				conn = DBUtility.establishConnection();
				ps = conn.prepareStatement(validateAdminQuery);
				ps.setString(1, adminPassword);
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
