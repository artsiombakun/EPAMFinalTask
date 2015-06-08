package command;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.CustomConstants;

public class ServiceChangeLanguagePage implements ServicePageCommand {

	@SuppressWarnings("unchecked")
	@Override
	public void executePage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String country ="", language = "";
		if("RU".equals(((Map <String, String>)request.getServletContext().
				getAttribute(CustomConstants.DICTIONARY_ATTR)).get(CustomConstants.LANGUAGE))){
			country ="RU";
			language = "RU";
		}
		Map <String, String> dictionary = new HashMap<String, String>();
		ResourceBundle locale = ResourceBundle.getBundle(CustomConstants.LOCALE_ADDRESS, new Locale(language, country));
		Enumeration<String> e = locale.getKeys();
		while(e.hasMoreElements()){
			String key = e.nextElement();
			dictionary.put(key, locale.getString(key));
		}
		request.getServletContext().setAttribute(CustomConstants.DICTIONARY_ATTR, dictionary);
		request.getRequestDispatcher(request.getParameter(CustomConstants.CURRENT_PAGE)).forward(request, response);
	}

}
