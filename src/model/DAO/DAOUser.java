package model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entities.Client;
import model.entities.User;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import constants.CustomConstants;
import exceptions.DAOException;
import exceptions.JDBCConnectionException;

/**
 * Work with DB for User
 */
public class DAOUser extends DAO{
	/**
	 * constants to queries
	 */
	private final String IS_USER_EXIST = "SELECT ID FROM user WHERE login = ?";
	private final String SIGN_UP_USER = "INSERT INTO user(firstname, lastname, login, password) VALUES(?,?,?,?)";
	private final String SIGN_UP_CLIENT = "INSERT INTO client(ID, firstname, lastname) VALUES(?,?,?)";
	private final String AUTHORIZATION = "SELECT ID, role FROM User WHERE login = ? AND password = ?";
	private final String CLIENT_LIST = "SELECT ID, firstname, lastname FROM Client";
	
	/**
	 *current instance 
	 * */
	private static DAOUser user;
	
	public static synchronized DAOUser getInstance() throws DAOException{
		if(user == null)user = new DAOUser();
		return user;
	}
	
	private DAOUser() throws DAOException {
		super();
	}

	/**
	 * sign up in a system as new client
	 */
	public boolean sugnUp(String firstname, String lastname, String login, String pswd) throws DAOException {
		PreparedStatement psSignUser = null, psSignClient = null, psIsEx = null;
		Connection conn = null;
		boolean rez = false;
		try {
				conn = cnr.getConnection();
				conn.setAutoCommit(false);
				psIsEx = (PreparedStatement) conn.prepareStatement(IS_USER_EXIST);
				psIsEx.setString(1, login);
				psSignUser = (PreparedStatement) conn.prepareStatement(SIGN_UP_USER);
				psSignUser.setString(1, firstname);
				psSignUser.setString(2, lastname);
				psSignUser.setString(3, login);
				psSignUser.setString(4, pswd);
				psSignClient = (PreparedStatement) conn.prepareStatement(SIGN_UP_CLIENT);
				psSignClient.setString(2, firstname);
				psSignClient.setString(3, lastname);
				if (!psIsEx.executeQuery().next()) {
					if(psSignUser.executeUpdate()==1){
						ResultSet rs = psIsEx.executeQuery();
						if(rs.next()){
							psSignClient.setInt(1, rs.getInt(1));
							if(psSignClient.executeUpdate()==1){
								conn.commit();
								rez = true;
							}
						}
					}else{
						conn.rollback();
						rez = false;
					}
				}
		} catch (JDBCConnectionException e) {
			throw new DAOException(CustomConstants.CONN_UNAVAILABLE, e);
		} catch (SQLException e) {
			throw new DAOException(CustomConstants.CAN_NOT_CONNECT, e);
		} finally {
			try {
				conn.setAutoCommit(true);
				psSignClient.close();
				psSignUser.close();
				conn.close();
				cnr.returnConnection(conn);
			} catch (SQLException | JDBCConnectionException e) {
				throw new DAOException(CustomConstants.CAN_NOT_CLOSE, e);
			}
		}
		return rez;
	}

	/**
	 * authorization into system
	 * */
	public User authorization(String login, String pswd) throws DAOException {
		PreparedStatement psAthr = null;
		Connection conn = null;
		User rez = null;
		try {
			conn = cnr.getConnection();
			psAthr = (PreparedStatement) conn.prepareStatement(AUTHORIZATION);
			psAthr.setString(1, login);
			psAthr.setString(2, pswd);
			ResultSet rs = psAthr.executeQuery();
			if (rs.next()) {
				rez = new User(rs.getInt(1), rs.getString(2));
			}
		} catch (JDBCConnectionException e) {
			throw new DAOException(CustomConstants.CONN_UNAVAILABLE, e);
		} catch (SQLException e) {
			throw new DAOException(CustomConstants.CAN_NOT_CONNECT, e);
		} finally {
			try {
				psAthr.close();
				conn.close();
				cnr.returnConnection(conn);
			} catch (SQLException | JDBCConnectionException e) {
				throw new DAOException(CustomConstants.CAN_NOT_CLOSE, e);
			}
		}
		return rez;
	}
	
	/**
	 * get list of clients at payment system 
	 * */
	public List<Client> getClientList() throws DAOException {
		PreparedStatement psInfo = null;
		Connection conn = null;
		List<Client> rez = new ArrayList<Client>();
		try {
			conn = cnr.getConnection();
			psInfo = (PreparedStatement) conn.prepareStatement(CLIENT_LIST);
			ResultSet rs = psInfo.executeQuery();
			if (rs.next()) {
				do {
					rez.add(new Client(rs.getInt(1), rs.getString(2), rs.getString(3)));
				} while (rs.next());
			}
		} catch (JDBCConnectionException e) {
			throw new DAOException(CustomConstants.CONN_UNAVAILABLE, e);
		} catch (SQLException e) {
			throw new DAOException(CustomConstants.CAN_NOT_CONNECT, e);
		} finally {
			try {
				psInfo.close();
				conn.close();
				cnr.returnConnection(conn);
			} catch (SQLException | JDBCConnectionException e) {
				throw new DAOException(CustomConstants.CAN_NOT_CLOSE, e);
			}
		}
		return rez;
	}
	
}