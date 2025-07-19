package com.skk.dao;


public interface SignInDao {
	boolean getUserPasswordByEmail(String userEmailId, String userPass);
}
