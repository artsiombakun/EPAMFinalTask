package tags;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import util.Config;
/**
 * This tag load dictionary locale for first visit 
 * */
@SuppressWarnings("serial")
public class LoadDefaultLocaleTag extends TagSupport {
	/**
	 * country of locale, default US
	 * */
	private String country;
	/**
	 * language of locale, default EN
	 * */
	private String language;

	public int doStartTag() throws JspException {
		if(pageContext.getServletContext().getAttribute(Config.DICTIONARY_ATTR)==null){
			Locale loc = new Locale(language, country);
			Map <String, String> dictionary = new HashMap<String, String>();
			ResourceBundle locale = ResourceBundle.getBundle(Config.LOCALE_ADDRESS, loc);
			Enumeration<String> e = locale.getKeys();
			while(e.hasMoreElements()){
				String key = e.nextElement();
				dictionary.put(key, locale.getString(key));
			}
			pageContext.getServletContext().setAttribute(Config.DICTIONARY_ATTR, dictionary);
		}
		return SKIP_BODY;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}
