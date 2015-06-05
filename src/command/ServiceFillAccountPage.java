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

public class ServiceFillAccountPage implements ServicePageCommand{
	private static Logger theLogger = Logger.getLogger(ServiceFillAccountPage.class);
	
	@Override
	public void executePage(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		try{
		try{
			  int id = ((User) request.getSession().getAttribute(CustomConstants.USER_ATTR)).getId();
			  int to = Integer.parseInt(request.getParameter(CustomConstants.TO_ACC_PARAM));
			  int sum = Integer.parseInt(request.getParameter(CustomConstants.SUM_PARAM));
			  boolean isOwn = false;
			  for( Account acc : DAOCard.getInstance().infoAboutAcc(id)){
				  if(acc.getId()==to)isOwn = true;
			  }if(isOwn){
				  if(DAOAccount.getInstance().fillAccount(to, sum)){
					  request.getSession().setAttribute(CustomConstants.ACCOUNT_LIST_ATTR, DAOCard.getInstance().infoAboutAcc(id));
					  request.setAttribute(CustomConstants.ERROR_ATTR, "Filling has done!");
				  }else
					  request.setAttribute(CustomConstants.ERROR_ATTR, "Filling failed!Caused by a)account do not exist; "
					  		+ "b)accounts is closed; c)internal error.");
			  }else
				  request.setAttribute(CustomConstants.ERROR_ATTR, "Filling failed!You are not the owner of source account!");
		  }catch(NumberFormatException e){
			  request.setAttribute(CustomConstants.ERROR_ATTR, "Input data should be integer!");
		  }
		  request.getRequestDispatcher(CustomConstants.FILL_BALANCE_PAGE).forward(request, response);
		}catch(DAOException e){
			  theLogger.error(e);
			  request.setAttribute(CustomConstants.ERROR_MSG_FOR_LOG,e.getMessage());
			  request.getRequestDispatcher(CustomConstants.ERROR_PAGE).forward(request, response);
		  }
	}
}
