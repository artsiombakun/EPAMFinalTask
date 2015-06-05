package command;
/**
 * @author Artyom
 * */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import util.HelperMethods;
import constants.CustomConstants;
import model.DAO.DAOCard;
import model.DAO.DAOUser;
import model.entities.Role;
import model.entities.User;
import exceptions.DAOException;
/**
 * Command for service login-page
 * */
public class ServiceLoginPage implements ServicePageCommand{
	private static Logger theLogger = Logger.getLogger(ServiceLoginPage.class);

	@Override
	public void executePage(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException {
		User auth=null;
		HttpSession session = request.getSession();
		try {
			auth = DAOUser.getInstance().authorization(HelperMethods.setEncoding(request.getParameter(CustomConstants.LOGIN_PARAM)), 
					HelperMethods.setEncoding(request.getParameter(CustomConstants.PASSWORD_PARAM)));
			if(auth==null){
					request.setAttribute(CustomConstants.ERROR_ATTR, "Incorrect login or password! Please, try again.");
					request.getRequestDispatcher(CustomConstants.LOG_IN_PAGE).forward(request, response);
			}else if(auth.getRole() == Role.ADMIN){
				session.setAttribute(CustomConstants.CLIENT_LIST_ATTR, DAOUser.getInstance().getClientList());
				session.setAttribute(CustomConstants.BLOCKED_ACCOUNTS_LIST_ATTR, DAOCard.getInstance().blockedAccList());
					
				session.setAttribute(CustomConstants.USER_ATTR, auth);
					request.getRequestDispatcher(CustomConstants.GET_INFO_ABOUT_CLIENT_PAGE).forward(request, response);
			}else{
				session.setAttribute(CustomConstants.USER_ATTR, auth);
				session.setAttribute(CustomConstants.ACCOUNT_LIST_ATTR, DAOCard.getInstance().infoAboutAcc(auth.getId()));
				request.getRequestDispatcher(CustomConstants.CLIENT_TRANSFER_PAGE).forward(request, response);
			}
		 }catch(DAOException e){
			  theLogger.error(e);
			  request.setAttribute(CustomConstants.ERROR_MSG_FOR_LOG,e.getMessage());
			  request.getRequestDispatcher(CustomConstants.ERROR_PAGE).forward(request, response);
		  }
	}

}
