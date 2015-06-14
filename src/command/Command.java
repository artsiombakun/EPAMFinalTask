package command;
/**
 * @author Artyom
 * */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Command for service any form at JSP-page
 * */
public interface Command {
	/**
	 * execute actions for service selected form at JSP-page
	 * */
	public void executePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
}
