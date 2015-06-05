package connectDB;
/**
 * @author Artyom
 * */
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.mysql.jdbc.Connection;

import constants.CustomConstants;
import exceptions.JDBCConnectionException;
/**
 * Get connection to DB, using data from source
 * */
public class JDBCConnector {
	/**
	 * Pool of connections to DB
	 * */
	private BlockingQueue<Connection> pool;
	
	private synchronized void initJDBCConnector() throws JDBCConnectionException {
		pool = new LinkedBlockingQueue<Connection>();
		ConfigurationManager cfg = ConfigurationManager.getInstance();
		try {
			Class.forName(cfg.getDriverName());
		} catch (ClassNotFoundException e) {
			throw new JDBCConnectionException(CustomConstants.CAN_NOT_LOAD_DRIVER, e);
		}
		for (int i = 0; i < 10; i++) {
			pool.add(createConnection(cfg));
		}
	}
	
	private synchronized Connection createConnection(ConfigurationManager cfg) throws JDBCConnectionException {
		Connection conn;
		try {
			conn = (Connection) DriverManager.getConnection(cfg.getURL(),
					cfg.getLogin(), cfg.getPassword());
		} catch (SQLException e) {
			throw new JDBCConnectionException(CustomConstants.CAN_NOT_CONNECT, e);
		}
		if (conn == null) {
			throw new JDBCConnectionException(
					CustomConstants.WRONG_DRIVER_TYPE + cfg.getDriverName() + ".");
		}
		return conn;
	}
	
	/**
	 * Retrieves and remove connection from pool
	 * @return connection to DB
	 * @throws JDBCConnectionException if pool is empty
	 * */
	public synchronized Connection getConnection() throws JDBCConnectionException {
		if(pool==null){
			initJDBCConnector();
		}
		Connection conn = null;
		try {
			conn = pool.poll(500, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			throw new JDBCConnectionException(CustomConstants.INTERRUPTED_ERROR, e);
		}
		if(conn == null){
			throw new JDBCConnectionException(CustomConstants.POOL_IS_EMPTY);
		}
		return conn;
	}
	/**
	 * Put connection into pool
	 * @throws JDBCConnectionException 
	 * */
	public void returnConnection(Connection conn) throws JDBCConnectionException{
		try {
			if(conn != null && !conn.isClosed()){
				pool.put(conn);
			}
		} catch (InterruptedException e) {
			throw new JDBCConnectionException(CustomConstants.INTERRUPTED_ERROR, e);
		}
	}

}
