package command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import constants.CustomConstants;
import model.DAO.DAOAccount;
import model.DAO.DAOCard;
import model.entities.Account;
import model.entities.User;
import exceptions.DAOException;
/**
 * Command for service user-payment page
 * */
public class ServiceTransferPage implements ServicePageCommand{
	private static Logger theLogger = Logger.getLogger(ServiceTransferPage.class);
	
	@Override
	public void executePage(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		try{
		try{
			  int id = ((User) request.getSession().getAttribute(CustomConstants.USER_ATTR)).getId();
			  int from = Integer.parseInt(request.getParameter(CustomConstants.FROM_ACC_PARAM));
			  int to = Integer.parseInt(request.getParameter(CustomConstants.TO_ACC_PARAM));
			  int sum = Integer.parseInt(request.getParameter(CustomConstants.SUM_PARAM));
			  boolean isOwn = false;
			  for( Account acc : DAOCard.getInstance().infoAboutAcc(id)){
				  if(acc.getId()==from)isOwn = true;
			  }if(isOwn){
				  if(DAOAccount.getInstance().transfer(from, to, sum)){
					  request.getSession().setAttribute(CustomConstants.ACCOUNT_LIST_ATTR, DAOCard.getInstance().infoAboutAcc(id));
					  request.setAttribute(CustomConstants.ERROR_ATTR, "Transfer has done!");
				  }else
					  request.setAttribute(CustomConstants.ERROR_ATTR, "Transfer failed!Caused by a)one/both accounts do not exist; "
					  		+ "b)one/both accounts are closed; c)you account has negative balance.");
			  }else
				  request.setAttribute(CustomConstants.ERROR_ATTR, "Transfer failed!You are not the owner of source account!");
		  }catch(NumberFormatException e){
			  request.setAttribute(CustomConstants.ERROR_ATTR, "Input data should be integer!");
		  }
		  request.getRequestDispatcher(CustomConstants.CLIENT_TRANSFER_PAGE).forward(request, response);
		}catch(DAOException e){
			  theLogger.error(e);
			  request.setAttribute(CustomConstants.ERROR_MSG_FOR_LOG,e.getMessage());
			  request.getRequestDispatcher(CustomConstants.ERROR_PAGE).forward(request, response);
		  }
	}

}
