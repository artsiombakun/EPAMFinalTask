package filters;

/**@author Artyom*/
import java.io.IOException;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import util.Config;

/**
 * Check the fields of sign-up form at non-empty
 * */
@WebFilter(value = "/*")
public class CheckFullFieldsFilter implements Filter {

	@Override
	public void destroy() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain fchain) throws IOException,
			ServletException {
		Map<String, String> msgs = (Map<String, String>) request.getServletContext().getAttribute(Config.DICTIONARY_ATTR);
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String errmsg = "";
		if (Config.SIGN_UP_COMMAND.equals(req.getParameter(Config.COMMAND_TYPE_PARAM))) {
			if (req.getParameter(Config.FIRSTNAME_PARAM).isEmpty())
				errmsg += msgs.get(Config.INPUT) + msgs.get(Config.FIRST_NAME) + "! <p>";
			if (req.getParameter(Config.LASTNAME_PARAM).isEmpty())
				errmsg += msgs.get(Config.INPUT) + msgs.get(Config.LAST_NAME) + "! <p>";
			if (req.getParameter(Config.LOGIN_PARAM).isEmpty())
				errmsg += msgs.get(Config.INPUT) + msgs.get(Config.LOGIN) + "! <p>";
			if (req.getParameter(Config.PASSWORD_PARAM).isEmpty())
				errmsg += msgs.get(Config.INPUT) + msgs.get(Config.PASSWORD) + "! <p>";
			if (req.getParameter(Config.CONFIRM_PASSWORD_PARAM).isEmpty())
				errmsg += msgs.get(Config.INPUT) + msgs.get(Config.CONFIRM_PASSWORD) + "!";
		}
		if (errmsg.isEmpty()) {
			fchain.doFilter(request, response);
		} else {
			req.setAttribute(Config.ERROR_ATTR, errmsg);
			req.getRequestDispatcher(Config.SIGN_UP_PAGE).forward(req, resp);
		}

	}

	@Override
	public void init(FilterConfig fconfig) throws ServletException {
	}

}