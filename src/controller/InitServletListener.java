package controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import connectDB.JDBCConnector;
@WebListener
public class InitServletListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		JDBCConnector.destroyPool();
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String path = this.getClass().getResource("/").getPath();
		System.setProperty("rootpath", path);
	}

}
