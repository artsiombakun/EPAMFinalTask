package filters;

/**@author Artyom*/
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import util.Config;

/**
 * Switch locale from English to Russian and vice versa
 * */
@WebFilter(value = "/*")
public class ChangeLanguageFilter implements Filter {

	@Override
	public void destroy() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain fchain) throws IOException, ServletException {
		if (Config.CHANGE_LANGUAGE_COMMAND.equals(request.getParameter(Config.COMMAND_TYPE_PARAM))) {
			String country = "", language = "";
			switch (((Map<String, String>) request.getServletContext()
					.getAttribute(Config.DICTIONARY_ATTR)).get(Config.LANGUAGE)
					.toUpperCase()) {
			case "EN":
				country = "EN";
				language = "EN";
				break;
			case "RU":
				country = "RU";
				language = "RU";
				break;
			}
			Map<String, String> dictionary = new HashMap<String, String>();
			Locale loc = new Locale(language, country);
			ResourceBundle locale = ResourceBundle.getBundle(Config.LOCALE_ADDRESS, loc);
			Enumeration<String> e = locale.getKeys();
			while (e.hasMoreElements()) {
				String key = e.nextElement();
				dictionary.put(key, locale.getString(key));
			}
			request.getServletContext().setAttribute(Config.DICTIONARY_ATTR, dictionary);
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.addCookie(new Cookie(Config.LOCALE_LANGUAGE, language));
			resp.addCookie(new Cookie(Config.LOCALE_COUNTRY, country));
		}
		fchain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
