package com.qbryx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.qbryx.domain.User;
import com.qbryx.enums.UserType;
import com.qbryx.manager.ConnectionManager;
import com.qbryx.util.DAOQuery;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	public User getUser(String username) {
		
		Connection connection = ConnectionManager.getConnection();
		
		User user = null;
		
		if(connection != null){
			PreparedStatement stmt;
				
			try {
				stmt = connection.prepareStatement(DAOQuery.SQL_GET_USER);
				stmt.setString(1, username);
				
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()){
					user = new User(rs.getInt("id"), UserType.valueOf(rs.getString("user_type")), rs.getString("username"), rs.getString("password"));
				}
				
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}			
		}
		
		
		return user;
	}
}
