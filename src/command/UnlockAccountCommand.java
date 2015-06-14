package command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DAO.DAOAccount;
import model.DAO.DAOCard;
import org.apache.log4j.Logger;

import util.Config;
import exceptions.DAOException;

public class UnlockAccountCommand implements Command{
	private static Logger theLogger = Logger.getLogger(UnlockAccountCommand.class);
	
	@Override
	public void executePage(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		  try{
			  id = Integer.parseInt(request.getParameter(Config.UNLOCK_ID_PARAM));
			  if(DAOAccount.getInstance().unlockAccount(id)){
				  request.setAttribute(Config.SUCCESS_ATTR, "Account unlocked!");
			  }else{
				  request.setAttribute(Config.ERROR_ATTR, "Unlocking failed!Caused by: "
				  		+ "a)account do not exist; b)account already open; c)internal error.");
			  }
			  reloadBlockedAccountsList(request, response);
			  request.getRequestDispatcher(Config.UNLOCK_ACCOUNT_PAGE).forward(request, response);
		  }catch(NumberFormatException e){
			  request.setAttribute(Config.ERROR_ATTR, "ID should be integer!");
			  request.getRequestDispatcher(Config.UNLOCK_ACCOUNT_PAGE).forward(request, response);
		  }catch(DAOException e){
			  theLogger.error(e);
			  request.setAttribute(Config.ERROR_MSG_FOR_LOG,e.getMessage());
			  request.getRequestDispatcher(Config.ERROR_PAGE).forward(request, response);
		  }
	}

	private void reloadBlockedAccountsList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException{
		HttpSession session = request.getSession();
		DAOCard card = DAOCard.getInstance();
		int items = (int) session.getAttribute(Config.RECORDS_PER_PAGE_TAG);
		session.setAttribute(Config.BLOCKED_ACCOUNTS_LIST_ATTR, card.blockedAccListPage(0, items));
		session.setAttribute(Config.BLOCKED_ACCS_LIST_PAGE_ATTR, 0);
		session.setAttribute(Config.BLOCKED_ACCS_LIST_TOTAL_PAGE_ATTR, 
				(int) Math.ceil(card.getBlockedAccListCountRecords() * 1.0 / items) - 1);
	}
	
}
