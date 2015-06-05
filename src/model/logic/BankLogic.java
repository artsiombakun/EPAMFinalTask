package model.logic;
/**
 * @author Artyom
 * */
import java.util.List;

import model.DAO.DAOAccount;
//import model.DAO.DAOAdmin;
//import model.DAO.DAOClient;
import model.DAO.DAOUser;
import model.entities.Account;
import model.entities.Client;
import model.entities.User;
import exceptions.BankException;
import exceptions.DAOException;
/**
 * class of BA, united work Users and Admins 
 * */
public class BankLogic {
	/**
	 * execute Admin's role
	 * */
	private DAOUser DAOuser;
	/**
	 * execute User's role
	 * */
//	private DAOAdmin DAOadmin;
//	
//	private DAOAccount DAOaccount;
//	
//	private DAOClient DAOclient;
//
//	public BankLogic() {
//		try {
//			DAOuser = new DAOUser();
//			DAOadmin = new DAOAdmin();
//			DAOaccount = new DAOAccount();
//			DAOclient = new DAOClient();
//		} catch (DAOException e) {
//			e.printStackTrace();
//		}
//	}
//	/**
//	 * get list of user's ID, name and last name
//	 * */
//	public List<Client> getUserList() throws BankException {
//		try {
//			return DAOadmin.getClientList();
//		} catch (DAOException e) {
//			throw new BankException(e);
//		}
//	}
//	/**
//	 * authorization in system
//	 * */
//	public User authorization(String login, String pswd)
//			throws BankException {
//		try {
//			return DAOuser.authorization(login, pswd);
//		} catch (DAOException e) {
//			throw new BankException(e);
//		}
//	}
//	/**
//	 * get accounts of select user
//	 * */
//	public List<Account> infoAboutAcc(int id) throws BankException {
//		try {
//			return DAOclient.infoAboutAcc(id);
//		} catch (DAOException e) {
//			throw new BankException(e);
//		}
//	}
//	/**
//	 * get all blocked accounts
//	 * */
//	public List<Account> blockedAccountsList() throws BankException {
//		try {
//			return DAOclient.blockedAccList();
//		} catch (DAOException e) {
//			throw new BankException(e);
//		}
//	}
//	/**
//	 * make transfer from account to account
//	 * */
//	public boolean transfer(int accFrom, int accTo, int sum) throws BankException {
//		try {
//			return DAOaccount.transfer(accFrom, accTo, sum);
//		} catch (DAOException e) {
//			throw new BankException(e);
//		}
//	}
//	/**
//	 * close account </p>rest transfered to other account
//	 * */
//	public boolean closeAccount(int accClose, int accTo) throws BankException {
//		try {
//			return DAOaccount.closeAccount(accClose, accTo);
//		} catch (DAOException e) {
//			throw new BankException(e);
//		}
//	}
//
//	/**
//	 * block select account
//	 * */
//	public boolean blockAccount(int accBlock) throws BankException {
//		try {
//			return DAOaccount.blockAccount(accBlock);
//		} catch (DAOException e) {
//			throw new BankException(e);
//		}
//	}
//	
//	/**
//	 * unlock select account
//	 * */
//	public boolean unlockAccount(int accUnlock) throws BankException {
//		try {
//			return DAOaccount.unlockAccount(accUnlock);
//		} catch (DAOException e) {
//			throw new BankException(e);
//		}
//	}
//	
//	/**
//	 * fill balance at select account
//	 * @param accTo 
//	 * @param sum 
//	 * */
//	public boolean fillAccount(int accTo, int sum) throws BankException {
//		try {
//			return DAOaccount.fillAccount(accTo, sum);
//		} catch (DAOException e) {
//			throw new BankException(e);
//		}
//	}
//	
//	/**
//	 * sugn up new client
//	 * @param firstname 
//	 * @param lastname 
//	 * @param login 
//	 * @param pswd 
//	 * */
//	public boolean signUp(String firstname, String lastname, String login, String pswd) throws BankException {
//		try {
//			return DAOuser.sugnUp(firstname, lastname, login, pswd);
//		} catch (DAOException e) {
//			throw new BankException(e);
//		}
//	}
//	
//	/**
//	 * create new account for select client
//	 * */
//	public boolean createAccount(int id) throws BankException {
//		try {
//			return DAOadmin.CreateAccount(id);
//		} catch (DAOException e) {
//			throw new BankException(e);
//		}
//	}
}
