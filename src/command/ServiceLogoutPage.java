package command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.CustomConstants;

public class ServiceLogoutPage implements ServicePageCommand{
	@Override
	public void executePage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute(CustomConstants.CLIENT_LIST_ATTR, null);
		request.getSession().setAttribute(CustomConstants.LAST_VISIT_TIME_ATTR, null);
		request.getSession().setAttribute(CustomConstants.ACCOUNT_LIST_ATTR, null);
		request.getSession().setAttribute(CustomConstants.VISITS_NUMBER_ATTR, null);
		request.getSession().setAttribute(CustomConstants.USER_ATTR, null);
		request.getSession().setAttribute(CustomConstants.BLOCKED_ACCOUNTS_LIST_ATTR, null);
		request.getRequestDispatcher(CustomConstants.LOG_IN_PAGE).forward(request, response);
		
	}

}
