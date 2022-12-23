package com.my;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MyConnections {

	private static MyConnections instance;
	private DataSource ds;
	public MyConnections(){
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/TestDB");

		} catch (NamingException e) {
			throw new RuntimeException(e);//illigual state exeption(Data sours); listener
		}
	}
	public static MyConnections getInstance(){
		if(instance ==null)
			instance =new MyConnections();
		return instance;
	}
	public DataSource getDs() {
		return ds;
	}

	public static Connection getConnection() {
		try {
			DataSource ds = null;

			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/TestDB");

			Connection con = ds.getConnection();
			return con;
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
		return null;
	}


}
