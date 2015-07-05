package command;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.DAOAccount;

import org.apache.log4j.Logger;

import util.Config;
import exceptions.DAOException;

public class CreateAccountCommand  implements Command{
	private static Logger theLogger = Logger.getLogger(CreateAccountCommand.class);
	
	@Override
	public void executePage(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		@SuppressWarnings("unchecked")
		Map<String, String> msgs = (Map<String, String>) request.getServletContext().getAttribute(Config.DICTIONARY_ATTR);
		  try{
			  id = Integer.parseInt(request.getParameter(Config.ID_PARAM));
			  if(DAOAccount.getInstance().createAccount(id)){
				  request.setAttribute(Config.SUCCESS_ATTR, msgs.get(Config.CREATE_DONE));
			  }else{
				  request.setAttribute(Config.ERROR_ATTR, msgs.get(Config.CREATE_FAIL) + msgs.get(Config.CREATE_FAIL_CAUSE));
			  }
			  request.getRequestDispatcher(Config.CREATE_ACCOUNT_PAGE).forward(request, response);
		  }catch(NumberFormatException e){
			  request.setAttribute(Config.ERROR_ATTR, msgs.get(Config.INT_ID));
			  request.getRequestDispatcher(Config.CREATE_ACCOUNT_PAGE).forward(request, response);
		  }catch(DAOException e){
			  theLogger.error(e);
			  request.setAttribute(Config.ERROR_MSG_FOR_LOG,e.getMessage());
			  request.getRequestDispatcher(Config.ERROR_PAGE).forward(request, response);
		  }
	}

}
