package model.DAO;

import connectDB.JDBCConnector;
import exceptions.DAOException;
/**
 * @author Artyom
 */
class DAO {
	/**
	 * To connect to DB
	 */
	protected JDBCConnector cnr;

	public DAO() throws DAOException{
		cnr = new JDBCConnector();
	}

	public JDBCConnector getJDBCConnector() {
		return cnr;
	}

}
