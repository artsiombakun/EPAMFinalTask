package command;

/**
 * @author Artyom
 * */
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import util.Config;
import model.DAO.DAOCard;
import model.DAO.DAOUser;
import model.entities.Account;
import model.entities.Role;
import model.entities.User;
import exceptions.DAOException;

public class LoginCommand implements Command {
	private static Logger theLogger = Logger.getLogger(LoginCommand.class);

	@SuppressWarnings("unchecked")
	@Override
	public void executePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User auth = null;
		HttpSession session = request.getSession();
		DAOUser user = null;
		try {
			user = DAOUser.getInstance();
			auth = user.authorization(new String(request.getParameter(Config.LOGIN_PARAM).getBytes("ISO-8859-1"), "UTF-8"),
					new String(request.getParameter(Config.PASSWORD_PARAM).getBytes("ISO-8859-1"), "UTF-8"));
			if (auth == null) {
				request.setAttribute(Config.ERROR_ATTR,
						((Map<String, String>) request.getServletContext().getAttribute(Config.DICTIONARY_ATTR))
								.get(Config.AUTH_FAIL));
				request.getRequestDispatcher(Config.LOG_IN_PAGE).forward(request, response);
			} else if (auth.getRole() == Role.ADMIN) {
				session.setAttribute(Config.USER_ATTR, auth);
				session.setAttribute(Config.RECORDS_PER_PAGE_TAG, Config.ITEMS_PER_PAGE);
				reloadClientList(request, response);
				reloadBlockedAccountsList(request, response);
				request.getRequestDispatcher(Config.GET_INFO_ABOUT_CLIENT_PAGE).forward(request, response);
				// response.sendRedirect(Config.GET_INFO_ABOUT_CLIENT_PAGE);
			} else {
				session.setAttribute(Config.USER_ATTR, auth);
				session.setAttribute(Config.RECORDS_PER_PAGE_TAG, Config.ITEMS_PER_PAGE);
				reloadAccountsList(auth.getId(), request, response);
				request.getRequestDispatcher(Config.CLIENT_TRANSFER_PAGE).forward(request, response);
				// response.sendRedirect(Config.CLIENT_TRANSFER_PAGE);
			}
		} catch (DAOException e) {
			theLogger.error(e);
			request.setAttribute(Config.ERROR_MSG_FOR_LOG, e.getMessage());
			request.getRequestDispatcher(Config.ERROR_PAGE).forward(request, response);
		}
	}

	private void reloadClientList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,
			DAOException {
		HttpSession session = request.getSession();
		DAOUser user = DAOUser.getInstance();
		int items = (int) session.getAttribute(Config.RECORDS_PER_PAGE_TAG);
		session.setAttribute(Config.CLIENT_LIST_ATTR, user.getClientListPage(0, items));
		session.setAttribute(Config.CLIENT_LIST_PAGE_ATTR, 0);
		session.setAttribute(Config.CLIENT_LIST_TOTAL_PAGE_ATTR,
				(int) Math.ceil(user.getClientListCountRecords() * 1.0 / items) - 1);
	}

	private void reloadBlockedAccountsList(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException, DAOException {
		HttpSession session = request.getSession();
		DAOCard card = DAOCard.getInstance();
		int items = (int) session.getAttribute(Config.RECORDS_PER_PAGE_TAG);
		session.setAttribute(Config.BLOCKED_ACCOUNTS_LIST_ATTR, card.blockedAccListPage(0, items));
		session.setAttribute(Config.BLOCKED_ACCS_LIST_PAGE_ATTR, 0);
		session.setAttribute(Config.BLOCKED_ACCS_LIST_TOTAL_PAGE_ATTR,
				(int) Math.ceil(card.getBlockedAccListCountRecords() * 1.0 / items) - 1);
	}

	private void reloadAccountsList(int id, HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException, DAOException {
		HttpSession session = request.getSession();
		List<Account> acclist = DAOCard.getInstance().infoAboutAcc(id);
		session.setAttribute(Config.ACCOUNT_LIST_ATTR, acclist);
		session.setAttribute(Config.ACC_LIST_PAGE_ATTR, 0);
		session.setAttribute(Config.ACC_LIST_TOTAL_PAGE_ATTR, (int) Math.ceil(acclist.size() * 1.0 / Config.ITEMS_PER_PAGE) - 1);
	}

}
