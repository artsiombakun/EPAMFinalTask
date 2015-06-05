package filters;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import constants.CustomConstants;

public class CheckFullFieldsFilter implements Filter{

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain fchain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		//String contextPath = req.getContextPath();
		String errmsg = "";
		if(CustomConstants.SIGN_UP_COMMAND.equals(req.getParameter(CustomConstants.COMMAND_TYPE_PARAM))){
			if(req.getParameter(CustomConstants.FIRSTNAME_PARAM).isEmpty())
				errmsg+="Input first name! ";
			if(req.getParameter(CustomConstants.LASTNAME_PARAM).isEmpty())
				errmsg+="Input last name! ";
			if(req.getParameter(CustomConstants.LOGIN_PARAM).isEmpty())
				errmsg+="Input login! ";
			if(req.getParameter(CustomConstants.PASSWORD_PARAM).isEmpty())
				errmsg+="Input password! ";
			if(req.getParameter(CustomConstants.CONFIRM_PASSWORD_PARAM).isEmpty())
				errmsg+="Input confirm password!";
		}
		if(errmsg.isEmpty())
		{
			fchain.doFilter(request, response);
		}
		else
		{
			req.setAttribute(CustomConstants.ERROR_ATTR, errmsg);
			req.getRequestDispatcher(CustomConstants.SIGN_UP_PAGE).forward(req, resp);
			//resp.sendRedirect(contextPath + "/log.jsp");
		}

	}

	@Override
	public void init(FilterConfig fconfig) throws ServletException {
	}

}