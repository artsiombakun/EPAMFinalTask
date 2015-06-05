package model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entities.Account;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import constants.CustomConstants;
import exceptions.DAOException;
import exceptions.JDBCConnectionException;

public class DAOCard extends DAO{
	/**
	 * constants to queries
	 * */
	private final String INFO_ABOUT_ACC = "SELECT account.ID, balance, status "
			+ "FROM account INNER JOIN card ON account.ID = card.accountID WHERE ownerID = ?";
	private final String BLOCKED_ACC_LIST = "SELECT ID, balance, status FROM account WHERE status='blocked'";
	
	/**
	 *current instance 
	 * */
	private static DAOCard card;
	
	public static synchronized DAOCard getInstance() throws DAOException{
		if(card==null)card = new DAOCard();
		return card;
	}
	
	private DAOCard() throws DAOException {
		super();
	}
	
	/**
	 * get accounts of select client
	 * */
	public List<Account> infoAboutAcc(int id) throws DAOException {
		PreparedStatement psInfo = null;
		Connection conn = null;
		List<Account> rez = new ArrayList<Account>();
		try {
			conn = cnr.getConnection();
			psInfo = (PreparedStatement) conn.prepareStatement(INFO_ABOUT_ACC);
			psInfo.setInt(1, id);
			ResultSet rs = psInfo.executeQuery();
			if (rs.next()) {
				do {
					rez.add(new Account(rs.getInt(1), rs.getInt(2), rs.getString(3)));
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

	/** 
	* get accounts of select client
	 * */
	public List<Account> blockedAccList() throws DAOException {
		PreparedStatement psInfo = null;
		Connection conn = null;
		List<Account> rez = new ArrayList<Account>();
		try {
			conn = cnr.getConnection();
			psInfo = (PreparedStatement) conn.prepareStatement(BLOCKED_ACC_LIST);
			ResultSet rs = psInfo.executeQuery();
			if (rs.next()) {
				do {
					rez.add(new Account(rs.getInt(1), rs.getInt(2), rs.getString(3)));
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
