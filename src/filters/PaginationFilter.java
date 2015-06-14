package filters;
/**@author Artyom*/
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.DAO.DAOCard;
import model.DAO.DAOUser;
import exceptions.DAOException;
import util.Config;
/**
 * Execute pagination at multipage lists
 * */
@WebFilter(value="/*")
public class PaginationFilter implements Filter{

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain fchain) throws IOException, ServletException {
		if (Config.PAGINATION_COMMAND.equals(request.getParameter(Config.COMMAND_TYPE_PARAM))) {
			HttpSession session = ((HttpServletRequest)request).getSession();
			try {
				int pageNum = 0;
				String listName = request.getParameter(Config.LIST_NAME_PARAM);
				int items = (int) session.getAttribute(Config.RECORDS_PER_PAGE_TAG);
					switch(listName){
						case Config.CLIENT_LIST_ATTR:
							pageNum = (int) session.getAttribute(Config.CLIENT_LIST_PAGE_ATTR);
							pageNum = iteratePageNum(pageNum, request, response);
							session.setAttribute(listName, DAOUser.getInstance().getClientListPage(pageNum, items));
							session.setAttribute(Config.CLIENT_LIST_PAGE_ATTR, pageNum);
							break;
						case Config.ACCOUNT_LIST_ATTR:
							pageNum = (int) session.getAttribute(Config.ACC_LIST_PAGE_ATTR);
							pageNum = iteratePageNum(pageNum, request, response);
							session.setAttribute(Config.ACC_LIST_PAGE_ATTR, pageNum);
							break;
						case Config.BLOCKED_ACCOUNTS_LIST_ATTR:
							pageNum = (int) session.getAttribute(Config.BLOCKED_ACCS_LIST_PAGE_ATTR);
							pageNum = iteratePageNum(pageNum, request, response);
							session.setAttribute(listName, DAOCard.getInstance().blockedAccListPage(pageNum, items));
							session.setAttribute(Config.BLOCKED_ACCS_LIST_PAGE_ATTR, pageNum);
							break;
					}
			 }catch(DAOException e){
				  request.setAttribute(Config.ERROR_MSG_FOR_LOG,e.getMessage());
				  request.getRequestDispatcher(Config.ERROR_PAGE).forward(request, response);
			 }
		}
		fchain.doFilter(request, response);
	}
	
	private int iteratePageNum( int pageNum, ServletRequest request, ServletResponse response)
			throws ServletException, IOException{
		switch(request.getParameter(Config.PAGE_DIRECTION_PARAM)){
		case Config.NEXT_PAGE_TAG:
			++pageNum;
			break;
		case Config.PREVIOUS_PAGE_TAG:
			--pageNum;
			break;
		}
		return pageNum;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
