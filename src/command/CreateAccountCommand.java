package command;

import java.io.IOException;

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
		  try{
			  id = Integer.parseInt(request.getParameter(Config.ID_PARAM));
			  if(DAOAccount.getInstance().createAccount(id)){
				  request.setAttribute(Config.ERROR_ATTR, "Account created!");
			  }else{
				  request.setAttribute(Config.ERROR_ATTR, "Creation failed!Caused by: "
				  		+ "a)client do not exist; b)internal error.");
			  }
			  request.getRequestDispatcher(Config.CREATE_ACCOUNT_PAGE).forward(request, response);
		  }catch(NumberFormatException e){
			  request.setAttribute(Config.ERROR_ATTR, "ID should be integer!");
			  request.getRequestDispatcher(Config.CREATE_ACCOUNT_PAGE).forward(request, response);
		  }catch(DAOException e){
			  theLogger.error(e);
			  request.setAttribute(Config.ERROR_MSG_FOR_LOG,e.getMessage());
			  request.getRequestDispatcher(Config.ERROR_PAGE).forward(request, response);
		  }
	}

}
