package base.module.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(value = UserNotValidException.class)
	public ResponseEntity<String> userNotValidException(UserNotValidException ex) {

		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = DuplicateUsernameException.class)
	public ResponseEntity<String> exception(DuplicateUsernameException ex) {
		
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = AccessDeniedException.class)
	public ResponseEntity<String> accessDeniedException(AccessDeniedException ex) {
		
		return new ResponseEntity<String>("Access to reource is restricted: "+ex.getMessage(), HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String> exception(Exception ex) {
		
		return new ResponseEntity<String>("Internal server error!!"+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
