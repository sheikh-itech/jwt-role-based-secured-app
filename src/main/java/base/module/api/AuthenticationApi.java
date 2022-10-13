package base.module.api;

import base.module.beans.AuthenticationRequest;
import base.module.beans.AuthenticationResponse;
import base.module.utils.JWTUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("authenticate")
public class AuthenticationApi {

	private AuthenticationManager authenticationManager;
	private JWTUtil jwtTokenUtil;
	//private UserAuthService authService;
	
	public AuthenticationApi(JWTUtil jwtTokenUtil, AuthenticationManager authManager) {
		this.jwtTokenUtil = jwtTokenUtil;
		//this.authService = authService;
		this.authenticationManager = authManager;
	}

	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
			);
		}
		catch (BadCredentialsException ex) {
			throw new Exception("Incorrect username or password", ex);
		}

		/*
		final UserDetails userDetails = authService
				.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));*/
		
		final String jwt = jwtTokenUtil.generateTokenUsingUsername(authRequest.getUsername());
		
		return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse(jwt), HttpStatus.CREATED);
	}
}
