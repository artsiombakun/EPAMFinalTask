package controller;
/**@author Artyom*/
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.PropertyConfigurator;

import util.Config;
import command.Command;
import command.builder.CommandBuilder;
/**
 * It receives requests and passes them for service to the relevant commands
 * */
@SuppressWarnings("serial")
@WebServlet("/controller")
public class ServletController extends HttpServlet {
	
	private CommandBuilder commands = new CommandBuilder();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Command command = commands.getCommand((String) request.getParameter(Config.COMMAND_TYPE_PARAM));
		if (command != null) {
			command.executePage(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Command command = commands.getCommand((String) request.getParameter(Config.COMMAND_TYPE_PARAM));
		if (command != null) {
			command.executePage(request, response);
		}
	}

	@Override
	public void init() throws ServletException {
		PropertyConfigurator.configure(this.getClass().getResource("/")
				.getPath() + Config.LOG4G_CONFIG_FILE_ADDRESS);
	}

}
