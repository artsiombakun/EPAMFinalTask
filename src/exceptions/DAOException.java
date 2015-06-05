package exceptions;
/**
 * @author Artyom
 * */
/**
 * errors of DAO level
 * */
@SuppressWarnings("serial")
public class DAOException extends Exception {
	private String message;
	public DAOException(String msg, Exception e) {
		message = msg+"Caused by: "+e.getMessage();
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
