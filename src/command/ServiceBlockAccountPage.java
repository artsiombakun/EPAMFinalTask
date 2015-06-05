package command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.DAOAccount;
import model.DAO.DAOCard;
import model.entities.Account;
import model.entities.User;

import org.apache.log4j.Logger;

import constants.CustomConstants;
import exceptions.DAOException;

public class ServiceBlockAccountPage implements ServicePageCommand{
	private static Logger theLogger = Logger.getLogger(ServiceBlockAccountPage.class);
	
	@Override
	public void executePage(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException {
		try{
			try{
				  int id = ((User) request.getSession().getAttribute(CustomConstants.USER_ATTR)).getId();
				  int from = Integer.parseInt(request.getParameter(CustomConstants.BLOCK_ID_PARAM));
				  boolean isOwn = false;
				  DAOCard card = DAOCard.getInstance();
				  for( Account acc : card.infoAboutAcc(id)){
					  if(acc.getId()==from)isOwn = true;
				  }if(isOwn){
					  if(DAOAccount.getInstance().blockAccount(from)){
						  request.getSession().setAttribute(CustomConstants.ACCOUNT_LIST_ATTR, card.infoAboutAcc(id));
						  request.setAttribute(CustomConstants.ERROR_ATTR, "Blocking done!");
					  }else
						  request.setAttribute(CustomConstants.ERROR_ATTR, 
								  "Blocking failed!Caused by: a)account do not exist b)accoun already blocked c)internal error.");
				  }else
					  request.setAttribute(CustomConstants.ERROR_ATTR, "Blocking failed!You are not the owner of source account!");
			  }catch(NumberFormatException e){
				  request.setAttribute(CustomConstants.ERROR_ATTR, "Input data should be integer!");
			  }
			  request.getRequestDispatcher(CustomConstants.BLOCK_ACCOUNT_CLIENT_PAGE).forward(request, response);
		}catch(DAOException e){
			  theLogger.error(e);
			  request.setAttribute(CustomConstants.ERROR_MSG_FOR_LOG,e.getMessage());
			  request.getRequestDispatcher(CustomConstants.ERROR_PAGE).forward(request, response);
		}
	}
}
