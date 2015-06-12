package command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.DAOUser;

import org.apache.log4j.Logger;

import util.Config;
import exceptions.DAOException;

public class SignUpCommand implements Command{
	private static Logger theLogger = Logger.getLogger(SignUpCommand.class);
	
	@Override
	public void executePage(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException {
		try {
			String fname = new String(request.getParameter(Config.FIRSTNAME_PARAM).getBytes("ISO-8859-1"),"UTF-8"),
					lname = new String(request.getParameter(Config.LASTNAME_PARAM).getBytes("ISO-8859-1"),"UTF-8"),
					login = new String(request.getParameter(Config.LOGIN_PARAM).getBytes("ISO-8859-1"),"UTF-8"),
					pswd = new String(request.getParameter(Config.PASSWORD_PARAM).getBytes("ISO-8859-1"),"UTF-8");
			if(pswd.equals(new String(request.getParameter(Config.CONFIRM_PASSWORD_PARAM).getBytes("ISO-8859-1"),"UTF-8"))){
				if(DAOUser.getInstance().sugnUp(fname, lname, login, pswd)){
					new LoginCommand().executePage(request, response);
				}
				else{
					request.setAttribute(Config.ERROR_ATTR, "This login is already in use!");
					request.getRequestDispatcher(Config.SIGN_UP_PAGE).forward(request, response);
				}
			}else{
				request.setAttribute(Config.ERROR_ATTR, "The password is not confirmed!");
				request.getRequestDispatcher(Config.SIGN_UP_PAGE).forward(request, response);
			}
		 }catch(DAOException e){
			  theLogger.error(e);
			  request.setAttribute(Config.ERROR_MSG_FOR_LOG,e.getMessage());
			  request.getRequestDispatcher(Config.ERROR_PAGE).forward(request, response);
		  }
	}
}
