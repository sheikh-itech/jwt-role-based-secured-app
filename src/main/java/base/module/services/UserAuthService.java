package base.module.services;

import java.util.Optional;

import base.module.beans.UserDetail;
import base.module.repositories.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService implements UserDetailsService {

	@Autowired
	private RegistrationRepository registerRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<UserDetail> user = registerRepo.findByUsername(username);
		user.orElseThrow(()->new UsernameNotFoundException("User Not Found: "+username));

		return user.get();
	}
}
