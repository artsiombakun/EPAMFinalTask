package command;
/**
 * @author Artyom
 * */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Command for service any JSP-page
 * */
public interface ServicePageCommand {
	/**
	 * execute actions for service JSP-page
	 * */
	public void executePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
}
