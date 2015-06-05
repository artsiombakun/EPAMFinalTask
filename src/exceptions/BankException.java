package exceptions;
/**
 * @author Artyom
 * */
/**
 * errors at business-logic level
 * */
@SuppressWarnings("serial")
public class BankException extends Exception {
	private String msg;
	public BankException(Exception e) {
		msg = e.getMessage();
	}
	@Override
	public String getMessage() {
		return msg;
	}
}
