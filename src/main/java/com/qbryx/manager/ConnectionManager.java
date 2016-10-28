package com.qbryx.manager;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class ConnectionManager {

	public static Connection getConnection() {

		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/qbryx?user=root&password=password");
			
		} catch (ClassNotFoundException e) {
			
		} catch (SQLException e) {
			
		}

		return conn;
	}

	public static PreparedStatement prepareStatement(String query) {

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = getConnection().prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return preparedStatement;
	}
}
