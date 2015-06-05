package command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.DAOAccount;
import model.DAO.DAOCard;
import org.apache.log4j.Logger;

import constants.CustomConstants;
import exceptions.DAOException;

public class ServiceUnlockAccountPage implements ServicePageCommand{
	private static Logger theLogger = Logger.getLogger(ServiceUnlockAccountPage.class);
	
	@Override
	public void executePage(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		  try{
			  id = Integer.parseInt(request.getParameter(CustomConstants.UNLOCK_ID_PARAM));
			  if(DAOAccount.getInstance().unlockAccount(id)){
				  request.setAttribute(CustomConstants.ERROR_ATTR, "Account unlocked!");
			  }else{
				  request.setAttribute(CustomConstants.ERROR_ATTR, "Unlocking failed!Caused by: "
				  		+ "a)account do not exist; b)account already open; c)internal error.");
			  }
			  request.setAttribute(CustomConstants.BLOCKED_ACCOUNTS_LIST_ATTR, DAOCard.getInstance().blockedAccList());
			  request.getRequestDispatcher(CustomConstants.UNLOCK_ACCOUNT_PAGE).forward(request, response);
		  }catch(NumberFormatException e){
			  request.setAttribute(CustomConstants.ERROR_ATTR, "ID should be integer!");
			  request.getRequestDispatcher(CustomConstants.UNLOCK_ACCOUNT_PAGE).forward(request, response);
		  }catch(DAOException e){
			  theLogger.error(e);
			  request.setAttribute(CustomConstants.ERROR_MSG_FOR_LOG,e.getMessage());
			  request.getRequestDispatcher(CustomConstants.ERROR_PAGE).forward(request, response);
		  }
	}

}
