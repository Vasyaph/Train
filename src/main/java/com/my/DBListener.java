package com.my;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.SQLException;

@WebListener
public class DBListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			Connection con = MyConnections.getInstance().getDs().getConnection();

		} catch (SQLException e) {

			contextDestroyed(sce);
			throw new RuntimeException(e);
		}

	}


}
