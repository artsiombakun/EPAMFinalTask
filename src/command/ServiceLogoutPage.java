package command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.CustomConstants;

public class ServiceLogoutPage implements ServicePageCommand{
	@Override
	public void executePage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute(CustomConstants.CLIENT_LIST_ATTR, null);
		session.setAttribute(CustomConstants.LAST_VISIT_TIME_ATTR, null);
		session.setAttribute(CustomConstants.ACCOUNT_LIST_ATTR, null);
		session.setAttribute(CustomConstants.VISITS_NUMBER_ATTR, null);
		session.setAttribute(CustomConstants.USER_ATTR, null);
		session.setAttribute(CustomConstants.BLOCKED_ACCOUNTS_LIST_ATTR, null);
		session.invalidate();//destroy session
		request.getRequestDispatcher(CustomConstants.LOG_IN_PAGE).forward(request, response);
	}

}
