package command;
/**
 * @author Artyom
 * */
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DAO.DAOCard;
import model.entities.Account;

import org.apache.log4j.Logger;

import util.Config;
import exceptions.DAOException;
/**
 * Command for service get-info-admin page
 * */
public class GetInfoAboutClientCommand implements Command{
	private static Logger theLogger = Logger.getLogger(GetInfoAboutClientCommand.class);
	
	@Override
	public void executePage(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		  try{
			  id = Integer.parseInt(request.getParameter(Config.ID_PARAM));
			  reloadAccountsList(id, request, response);
			  request.getRequestDispatcher(Config.GET_INFO_ABOUT_CLIENT_PAGE).forward(request, response);
		  }catch(NumberFormatException e){
			  request.setAttribute(Config.ERROR_ATTR, "ID should be integer!");
			  request.getRequestDispatcher(Config.GET_INFO_ABOUT_CLIENT_PAGE).forward(request, response);
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
