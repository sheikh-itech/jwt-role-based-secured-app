package base.module.api;

import base.module.beans.Response;
import base.module.beans.UserDetail;
import base.module.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("register")
public class RegistrationApi {

	@Autowired
	private RegistrationService registerService;
	
	
	//@RolesAllowed("ADMIN")
	@RequestMapping(value="/user", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> registerUser(@RequestBody UserDetail user) {
		
		Response response = new Response();
		try {
			
			user = registerService.registerUserDetails(user);
			user.setPassword("*****");
			response.setData(user);
			response.setMessage("User registration success");
			response.setStatus(HttpStatus.CREATED);
			
		} catch(Exception ex) {
			response.setError(ex.getMessage());
			response.setMessage("User registration failure");
			response.setStatus(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Response>(response, response.getStatus());
	}
}
