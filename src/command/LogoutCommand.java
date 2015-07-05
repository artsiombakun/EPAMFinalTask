package command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.Config;

public class LogoutCommand implements Command{
	@Override
	public void executePage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute(Config.CLIENT_LIST_ATTR, null);
		session.setAttribute(Config.ACCOUNT_LIST_ATTR, null);
		session.setAttribute(Config.USER_ATTR, null);
		session.setAttribute(Config.BLOCKED_ACCOUNTS_LIST_ATTR, null);
		session.invalidate();//destroy session
		//request.getRequestDispatcher(Config.LOG_IN_PAGE).forward(request, response);
		response.sendRedirect(Config.BASE_APPLICATION_URL+Config.LOG_IN_PAGE);
	}

}
