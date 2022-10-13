package base.module.exceptions;

public class UserNotValidException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserNotValidException(String message) {
		super(message);
	}
}
