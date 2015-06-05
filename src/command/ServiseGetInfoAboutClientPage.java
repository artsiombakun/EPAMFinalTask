package command;
/**
 * @author Artyom
 * */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import constants.CustomConstants;
import model.DAO.DAOCard;
import exceptions.DAOException;
/**
 * Command for service get-info-admin page
 * */
public class ServiseGetInfoAboutClientPage implements ServicePageCommand{
	private static Logger theLogger = Logger.getLogger(ServiseGetInfoAboutClientPage.class);
	
	@Override
	public void executePage(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		  try{
			  id = Integer.parseInt(request.getParameter(CustomConstants.ID_PARAM));
			  request.setAttribute(CustomConstants.ACCOUNT_LIST_ATTR, DAOCard.getInstance().infoAboutAcc(id));
			  request.getRequestDispatcher(CustomConstants.GET_INFO_ABOUT_CLIENT_PAGE).forward(request, response);
		  }catch(NumberFormatException e){
			  request.setAttribute(CustomConstants.ERROR_ATTR, "ID should be integer!");
			  request.getRequestDispatcher(CustomConstants.GET_INFO_ABOUT_CLIENT_PAGE).forward(request, response);
		  }catch(DAOException e){
			  theLogger.error(e);
			  request.setAttribute(CustomConstants.ERROR_MSG_FOR_LOG,e.getMessage());
			  request.getRequestDispatcher(CustomConstants.ERROR_PAGE).forward(request, response);
		  }
	}

}
