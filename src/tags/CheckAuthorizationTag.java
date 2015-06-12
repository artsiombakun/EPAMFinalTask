package tags;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import util.Config;
import command.LogoutCommand;
import model.entities.Role;
import model.entities.User;

@SuppressWarnings("serial")
public class CheckAuthorizationTag extends TagSupport {
	
	private Role role;
	
	public void setRole(String role){
		this.role = Role.valueOf(role.toUpperCase());
	}
	
	public int doStartTag() throws JspException {
		try {
			HttpSession session = pageContext.getSession();
		     if(session==null){
		    	 pageContext.forward(Config.LOG_IN_PAGE);
		     }else{
		    	 User currUser = (User) session.getAttribute(Config.USER_ATTR);
		    	 if( currUser == null || currUser.getRole()!=role){
		    		 new LogoutCommand().executePage(
		    				 (HttpServletRequest)pageContext.getRequest(), (HttpServletResponse)pageContext.getResponse());
		    	 }
		     }
		} catch (IOException | ServletException e) {
		     throw new JspException(e.getMessage());
		}
		return SKIP_BODY;
	}
}
