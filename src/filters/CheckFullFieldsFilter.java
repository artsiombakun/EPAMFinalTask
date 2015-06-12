package filters;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import util.Config;
@WebFilter(value="/controller")
public class CheckFullFieldsFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain fchain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String errmsg = "";
		if (Config.SIGN_UP_COMMAND.equals(req.getParameter(Config.COMMAND_TYPE_PARAM))) {
			if (req.getParameter(Config.FIRSTNAME_PARAM).isEmpty())
				errmsg += "Input first name! ";
			if (req.getParameter(Config.LASTNAME_PARAM).isEmpty())
				errmsg += "Input last name! ";
			if (req.getParameter(Config.LOGIN_PARAM).isEmpty())
				errmsg += "Input login! ";
			if (req.getParameter(Config.PASSWORD_PARAM).isEmpty())
				errmsg += "Input password! ";
			if (req.getParameter(Config.CONFIRM_PASSWORD_PARAM)
					.isEmpty())
				errmsg += "Input confirm password!";
		}
		if (errmsg.isEmpty()) {
			fchain.doFilter(request, response);
		} else {
			req.setAttribute(Config.ERROR_ATTR, errmsg);
			req.getRequestDispatcher(Config.SIGN_UP_PAGE).forward(req,
					resp);
		}

	}

	@Override
	public void init(FilterConfig fconfig) throws ServletException {
	}

}