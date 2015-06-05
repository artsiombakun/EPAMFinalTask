package tags;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import model.entities.Role;
import model.entities.User;
import constants.CustomConstants;

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
		    	 pageContext.forward(CustomConstants.LOG_IN_PAGE);
		     }else{
		    	 User currUser = (User) session.getAttribute(CustomConstants.USER_ATTR);
		    	 if( currUser == null || currUser.getRole()!=role){
		    		 pageContext.forward(CustomConstants.LOG_IN_PAGE);
		    	 }
		     }
		} catch (IOException | ServletException e) {
		     throw new JspException(e.getMessage());
		}
		return SKIP_BODY;
	}
}
