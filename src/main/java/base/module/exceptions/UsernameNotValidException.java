package base.module.exceptions;

public class UsernameNotValidException extends Exception {

	private static final long serialVersionUID = 1L;

	public UsernameNotValidException(String message) {
		super(message);
	}
}
