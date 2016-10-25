package com.qbryx.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.qbryx.domain.User;
import com.qbryx.managers.ConnectionManager;
import com.qbryx.util.DAOQuery;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	public User getUser(String username) {
		User user = null;
		
		if(ConnectionManager.getConnection() != null){
			PreparedStatement stmt;
				
			try {
				stmt = ConnectionManager.prepareStatement(DAOQuery.SQL_GET_USER);
				stmt.setString(1, username);
				
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()){
					user = new User(rs.getInt("id"), rs.getString("user_type"), rs.getString("username"), rs.getString("password"));
				}
				
				ConnectionManager.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
			
		}
		
		
		return user;
	}

	@Override
	public String getPassword(String username) {
		
		String password = "";
		
		if(ConnectionManager.getConnection() != null){
			PreparedStatement stmt;
				
			try {
				stmt = ConnectionManager.prepareStatement(DAOQuery.SQL_GET_USER);
				stmt.setString(1, username);
				
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()){
					password = rs.getString("password");
				}
				
				ConnectionManager.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
			
		}
	
		return password;
	}
}
