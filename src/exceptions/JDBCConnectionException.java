package exceptions;
/**
 * @author Artyom
 * */
/**
 * errors during get connection
 * */
@SuppressWarnings("serial")
public class JDBCConnectionException extends Exception {
	private String message;

	public JDBCConnectionException(String msg, Exception e) {
		message = msg + "Caused by: " + e.getMessage();
	}

	public JDBCConnectionException(String msg) {
		message = msg;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
