package command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DAO.DAOAccount;
import model.DAO.DAOCard;
import model.entities.Account;
import model.entities.User;

import org.apache.log4j.Logger;

import util.Config;
import exceptions.DAOException;

public class FillAccountCommand implements Command{
	private static Logger theLogger = Logger.getLogger(FillAccountCommand.class);
	
	@Override
	public void executePage(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		try{
		try{
			  int id = ((User) request.getSession().getAttribute(Config.USER_ATTR)).getId();
			  int to = Integer.parseInt(request.getParameter(Config.TO_ACC_PARAM));
			  int sum = Integer.parseInt(request.getParameter(Config.SUM_PARAM));
			  boolean isOwn = false;
			  for( Account acc : DAOCard.getInstance().infoAboutAcc(id)){
				  if(acc.getId()==to)isOwn = true;
			  }if(isOwn){
				  if(DAOAccount.getInstance().fillAccount(to, sum)){
					 reloadAccountsList(id, request, response);
					  request.setAttribute(Config.ERROR_ATTR, "Filling has done!");
				  }else
					  request.setAttribute(Config.ERROR_ATTR, "Filling failed!Caused by a)account do not exist; "
					  		+ "b)accounts is closed; c)internal error.");
			  }else
				  request.setAttribute(Config.ERROR_ATTR, "Filling failed!You are not the owner of source account!");
		  }catch(NumberFormatException e){
			  request.setAttribute(Config.ERROR_ATTR, "Input data should be integer!");
		  }
		  request.getRequestDispatcher(Config.FILL_BALANCE_PAGE).forward(request, response);
		}catch(DAOException e){
			  theLogger.error(e);
			  request.setAttribute(Config.ERROR_MSG_FOR_LOG,e.getMessage());
			  request.getRequestDispatcher(Config.ERROR_PAGE).forward(request, response);
		  }
	}
	
	private void reloadAccountsList(int id, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException{
		HttpSession session = request.getSession();
		List <Account> acclist = DAOCard.getInstance().infoAboutAcc(id);
		  session.setAttribute(Config.ACCOUNT_LIST_ATTR, acclist);
		  session.setAttribute(Config.ACC_LIST_PAGE_ATTR, 0);
		  session.setAttribute(Config.ACC_LIST_TOTAL_PAGE_ATTR, 
					(int) Math.ceil(acclist.size() * 1.0 / Config.ITEMS_PER_PAGE) - 1);
	}
	
}
