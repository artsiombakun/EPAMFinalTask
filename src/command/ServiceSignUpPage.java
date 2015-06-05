package command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.DAOUser;
import org.apache.log4j.Logger;

import util.HelperMethods;
import constants.CustomConstants;
import exceptions.DAOException;

public class ServiceSignUpPage implements ServicePageCommand{
	private static Logger theLogger = Logger.getLogger(ServiceSignUpPage.class);
	
	@Override
	public void executePage(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException {
		try {
			String fname = HelperMethods.setEncoding(request.getParameter(CustomConstants.FIRSTNAME_PARAM)),
					lname = HelperMethods.setEncoding(request.getParameter(CustomConstants.LASTNAME_PARAM)),
					login = HelperMethods.setEncoding(request.getParameter(CustomConstants.LOGIN_PARAM)),
					pswd = HelperMethods.setEncoding(request.getParameter(CustomConstants.PASSWORD_PARAM));
			if(pswd.equals(HelperMethods.setEncoding(request.getParameter(CustomConstants.CONFIRM_PASSWORD_PARAM)))){
				if(DAOUser.getInstance().sugnUp(fname, lname, login, pswd)){
					new ServiceLoginPage().executePage(request, response);
				}
				else{
					request.setAttribute(CustomConstants.ERROR_ATTR, "This login is already in use!");
					request.getRequestDispatcher(CustomConstants.SIGN_UP_PAGE).forward(request, response);
				}
			}else{
				request.setAttribute(CustomConstants.ERROR_ATTR, "The password is not confirmed!");
				request.getRequestDispatcher(CustomConstants.SIGN_UP_PAGE).forward(request, response);
			}
		 }catch(DAOException e){
			  theLogger.error(e);
			  request.setAttribute(CustomConstants.ERROR_MSG_FOR_LOG,e.getMessage());
			  request.getRequestDispatcher(CustomConstants.ERROR_PAGE).forward(request, response);
		  }
	}
}
