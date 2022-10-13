package base.module.utils;

import java.util.Objects;

import base.module.beans.UserDetail;
import base.module.exceptions.UserNotValidException;

public class ValidationUtil {

	public static void validateUser(UserDetail user) throws Exception {
		
		StringBuilder error = new StringBuilder();
		if(null==user)
			error.append("User information not found");
		if(Objects.isNull(user.getUsername()) || user.getUsername().isBlank())
			error.append("Username length should be more then 6");
		
		String password = user.getPassword();
		if(Objects.isNull(password) || password.isBlank() || password.length()<5 || password.length()>10)
			error.append("Password length should be between 5 to 10");
		if(Objects.isNull(user.getAuthorities()) || user.getAuthorities().isEmpty())
			error.append("Provide at least one Authority");
		
		if(error.length()>0)
			throw new UserNotValidException(error.toString());
	}
}
