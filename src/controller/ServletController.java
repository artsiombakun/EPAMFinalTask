package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.PropertyConfigurator;

import command.ServicePageCommand;
import command.builder.CommandBuilder;
import constants.CustomConstants;

@SuppressWarnings("serial")
@WebServlet("/controller")
public class ServletController extends HttpServlet {
	CommandBuilder commands = new CommandBuilder();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServicePageCommand command = commands.getCommand((String) request
				.getParameter(CustomConstants.COMMAND_TYPE_PARAM));
		if (command != null) {
			command.executePage(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ServicePageCommand command = commands.getCommand((String) request
				.getParameter(CustomConstants.COMMAND_TYPE_PARAM));
		if (command != null) {
			command.executePage(request, response);
		}
	}

	@Override
	public void init() throws ServletException {
		PropertyConfigurator.configure(this.getClass().getResource("/")
				.getPath() + CustomConstants.LOG4G_CONFIG_FILE_ADDRESS);
	}

}
