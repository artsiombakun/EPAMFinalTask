package model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import util.Config;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import exceptions.DAOException;
import exceptions.JDBCConnectionException;

public class DAOAccount extends DAO{
	
	/**
	 * constants to queries
	 */
	private final String HAS_MONEY_TO_TRANSFER = "SELECT ID FROM account WHERE ID=? AND balance >= ? AND status = 'open'";
	private final String BLOCK_ACC = "UPDATE account SET status = 'blocked' WHERE ID = ?";
	private final String DELETE_ACC = "DELETE FROM card WHERE accountID = ?";
	private final String GET_BALANCE = "SELECT balance FROM account WHERE ID = ?";
	private final String TRANSFER_FROM = "UPDATE account SET balance = balance - ? WHERE ID=?";
	private final String TRANSFER_TO = "UPDATE account SET balance = balance + ? WHERE ID=?";
	private final String NOTE_ABOUT_TRANSFER = "INSERT INTO transfer(fromAcc, toAcc, amount) VALUES(?,?,?)";
	private final String IS_ACC_EXIST = "SELECT ID FROM account WHERE ID=? AND status = 'open'";
	private final String UNLOCK_ACC = "UPDATE account SET status = 'open' WHERE ID = ?";
	private final String IS_BLOCKED_ACC_EXIST = "SELECT ID FROM account WHERE ID=? AND status = 'blocked'";
	private final String IS_CLIENT_EXIST = "SELECT ID FROM client WHERE ID = ?";
	private final String CREATE_ACC = "INSERT INTO account (balance, status) VALUES(0, 'open')";
	private final String GET_ACC_ID = "SELECT LAST_INSERT_ID()";
	private final String CREATE_CARD = "INSERT INTO card (accountID, ownerID) VALUES(?,?)";
	
	/**
	 *current instance 
	 * */
	private static DAOAccount account;
	
	public static synchronized DAOAccount getInstance() throws DAOException{
		if(account==null)account = new DAOAccount();
		return account;
	}
	
	private DAOAccount() throws DAOException {
		super();
	}
	

	/**
	 * create new account for select client
	 */
	public boolean createAccount(int id) throws DAOException {
		PreparedStatement psCrAcc = null, psGetID = null, psCrCard = null, psIsEx = null;
		Connection conn = null;
		boolean rez = false;
		try {
				conn = cnr.getConnection();
				conn.setAutoCommit(false);
				psIsEx = (PreparedStatement) conn.prepareStatement(IS_CLIENT_EXIST);
				psIsEx.setInt(1, id);
				psCrAcc = (PreparedStatement) conn.prepareStatement(CREATE_ACC);
				psGetID = (PreparedStatement) conn.prepareStatement(GET_ACC_ID);
				psCrCard = (PreparedStatement) conn.prepareStatement(CREATE_CARD);
				psCrCard.setInt(2, id);
				if (psIsEx.executeQuery().next()) {
					if(psCrAcc.executeUpdate()==1){
						ResultSet rs = psGetID.executeQuery();
						if(rs.next()){
							psCrCard.setInt(1, rs.getInt(1));
							if(psCrCard.executeUpdate()==1){
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
			throw new DAOException(Config.CONN_UNAVAILABLE, e);
		} catch (SQLException e) {
			throw new DAOException(Config.CAN_NOT_CONNECT, e);
		} finally {
			try {
				conn.setAutoCommit(true);
				psCrCard.close();
				psCrAcc.close();
				cnr.returnConnection(conn);
			} catch (SQLException | JDBCConnectionException e) {
				throw new DAOException(Config.CAN_NOT_CLOSE, e);
			}
		}
		return rez;
	}
	
	/**
	 * make transfer from account to account
	 */
	public boolean transfer(int accFrom, int accTo, int sum) throws DAOException {
		if (accFrom == accTo)
			return true;
		if(sum<0)
			return false;
		PreparedStatement psHas = null, psFrom = null, psTo = null, psNote = null, psIsEx = null;
		Connection conn = null;
		boolean rez = false;
		try {
			if (accFrom != accTo) {
				conn = cnr.getConnection();
				conn.setAutoCommit(false);
				psHas = (PreparedStatement) conn
						.prepareStatement(HAS_MONEY_TO_TRANSFER);
				psHas.setInt(1, accFrom);
				psHas.setInt(2, sum);
				psIsEx = (PreparedStatement) conn
						.prepareStatement(IS_ACC_EXIST);
				psIsEx.setInt(1, accTo);
				psFrom = (PreparedStatement) conn
						.prepareStatement(TRANSFER_FROM);
				psFrom.setInt(1, sum);
				psFrom.setInt(2, accFrom);
				psTo = (PreparedStatement) conn.prepareStatement(TRANSFER_TO);
				psTo.setInt(1, sum);
				psTo.setInt(2, accTo);
				psNote = (PreparedStatement) conn
						.prepareStatement(NOTE_ABOUT_TRANSFER);
				psNote.setInt(1, accFrom);
				psNote.setInt(2, accTo);
				psNote.setInt(3, sum);
				if (psHas.executeQuery().next() && psIsEx.executeQuery().next()) {
					if(psFrom.executeUpdate()==1 && psTo.executeUpdate()==1 &&	psNote.executeUpdate()==1){
						conn.commit();
						rez = true;
					}else{
						conn.rollback();
						rez = false;
					}
				}else
					rez = false;
			}
		} catch (JDBCConnectionException e) {
			throw new DAOException(Config.CONN_UNAVAILABLE, e);
		} catch (SQLException e) {
			throw new DAOException(Config.CAN_NOT_CONNECT, e);
		} finally {
			try {
				conn.setAutoCommit(true);
				psHas.close();
				psFrom.close();
				psTo.close();
				psNote.close();
				cnr.returnConnection(conn);
			} catch (SQLException | JDBCConnectionException e) {
				throw new DAOException(Config.CAN_NOT_CLOSE, e);
			}
		}
		return rez;
	}
	
	/**
	 * close account </p>rest transfered to other account
	 */
	public boolean closeAccount(int accClose, int accTo) throws DAOException {
		if (accClose == accTo)
			return false;
		PreparedStatement psClose = null, psBalance = null, psIsEx = null;
		Connection conn = null;
		boolean rez = false;
		try {
			conn = cnr.getConnection();
			conn.setAutoCommit(false);
			psBalance = (PreparedStatement) conn.prepareStatement(GET_BALANCE);
			psBalance.setInt(1, accClose);
			psIsEx = (PreparedStatement) conn.prepareStatement(IS_ACC_EXIST);
			psIsEx.setInt(1, accTo);
			psClose = (PreparedStatement) conn.prepareStatement(DELETE_ACC);
			psClose.setInt(1, accClose);
			ResultSet rs = psBalance.executeQuery();
			rs.next();
			int sum = rs.getInt(1);
			if (psIsEx.executeQuery().next()) {
				if (sum >= 0 && transfer(accClose, accTo, sum) && psClose.executeUpdate()==1){
					conn.commit();
					rez = true;
				}else{
					conn.rollback();
					rez = false;
				}
			}else
				rez = false;
		} catch (JDBCConnectionException e) {
			throw new DAOException(Config.CONN_UNAVAILABLE, e);
		} catch (SQLException e) {
			throw new DAOException(Config.CAN_NOT_CONNECT, e);
		} finally {
			try {
				conn.setAutoCommit(true);
				psBalance.close();
				psClose.close();
				cnr.returnConnection(conn);
			} catch (SQLException | JDBCConnectionException e) {
				throw new DAOException(Config.CAN_NOT_CLOSE, e);
			}
		}
		return rez;
	}

	/**
	 * block select account
	 */
	public boolean blockAccount(int accBlock) throws DAOException {
		PreparedStatement psBlock = null, psIsEx = null;
		Connection conn = null;
		boolean rez = false;
		try {
			conn = cnr.getConnection();
			conn.setAutoCommit(false);
			psIsEx = (PreparedStatement) conn.prepareStatement(IS_ACC_EXIST);
			psIsEx.setInt(1, accBlock);
			psBlock = (PreparedStatement) conn.prepareStatement(BLOCK_ACC);
			psBlock.setInt(1, accBlock);
			if (psIsEx.executeQuery().next()) {
				if (psBlock.executeUpdate()==1){
					conn.commit();
					rez = true;
				}else{
					conn.rollback();
					rez = false;
				}
			}else
				rez = false;
		} catch (JDBCConnectionException e) {
			throw new DAOException(Config.CONN_UNAVAILABLE, e);
		} catch (SQLException e) {
			throw new DAOException(Config.CAN_NOT_CONNECT, e);
		}  finally {
			try {
				conn.setAutoCommit(true);
				psBlock.close();
				cnr.returnConnection(conn);
			} catch (SQLException | JDBCConnectionException e) {
				throw new DAOException(Config.CAN_NOT_CLOSE, e);
			}
		}
		return rez;
	}
	
	/**
	 * unlock select account
	 */
	public boolean unlockAccount(int accBlock) throws DAOException {
		PreparedStatement psBlock = null, psIsEx = null;
		Connection conn = null;
		boolean rez = false;
		try {
			conn = cnr.getConnection();
			conn.setAutoCommit(false);
			psIsEx = (PreparedStatement) conn.prepareStatement(IS_BLOCKED_ACC_EXIST);
			psIsEx.setInt(1, accBlock);
			psBlock = (PreparedStatement) conn.prepareStatement(UNLOCK_ACC);
			psBlock.setInt(1, accBlock);
			if (psIsEx.executeQuery().next()) {
				if (psBlock.executeUpdate()==1){
					conn.commit();
					rez = true;
				}else{
					conn.rollback();
					rez = false;
				}
			}else
				rez = false;
		} catch (JDBCConnectionException e) {
			throw new DAOException(Config.CONN_UNAVAILABLE, e);
		} catch (SQLException e) {
			throw new DAOException(Config.CAN_NOT_CONNECT, e);
		}  finally {
			try {
				conn.setAutoCommit(true);
				psBlock.close();
				cnr.returnConnection(conn);
			} catch (SQLException | JDBCConnectionException e) {
				throw new DAOException(Config.CAN_NOT_CLOSE, e);
			}
		}
		return rez;
	}
	
	/**
	 * fill balance at account
	 */
	public boolean fillAccount(int accTo, int sum) throws DAOException {
		if(sum<0)
			return false;
		PreparedStatement psTo = null, psIsEx = null;
		Connection conn = null;
		boolean rez = false;
		try {
				conn = cnr.getConnection();
				psIsEx = (PreparedStatement) conn
						.prepareStatement(IS_ACC_EXIST);
				psIsEx.setInt(1, accTo);
				psTo = (PreparedStatement) conn.prepareStatement(TRANSFER_TO);
				psTo.setInt(1, sum);
				psTo.setInt(2, accTo);
				if (sum>0 && psIsEx.executeQuery().next()) {
					if(psTo.executeUpdate()==1){
						rez = true;
					}else{
						rez = false;
					}
				}else
					rez = false;
		} catch (JDBCConnectionException | SQLException e) {
			throw new DAOException(Config.CAN_NOT_CONNECT, e);
		} finally {
			try {
				psTo.close();
				cnr.returnConnection(conn);
			} catch (SQLException | JDBCConnectionException e) {
				throw new DAOException(Config.CAN_NOT_CLOSE, e);
			}
		}
		return rez;
	}

}
