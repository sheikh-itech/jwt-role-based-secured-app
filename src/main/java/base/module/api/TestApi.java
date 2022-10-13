package base.module.api;

import javax.annotation.security.RolesAllowed;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestApi {

	private String style1 = "'color:lightgreen;font-size:25px;'";
	
	@RolesAllowed({"GUEST","ADMIN"})
	@RequestMapping("guest")
	public String guest() {
		
		return "<div style="+style1+">Welcome to secure app- guest page</div>";
	}
	
	//prePostEnabled=true
	//jsr250Enabled=false(default)
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@RequestMapping("user")
	public String user() {
		
		return "<div style="+style1+">Welcome to secure app- user page</div>";
	}
	
	@RolesAllowed("ADMIN")
	//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@RequestMapping("admin")
	public String admin() {
		
		return "<div style="+style1+">Welcome to secure app- admin page</div>";
	}
	
	@RequestMapping("home")
	public String home() {
		
		return "<div style="+style1+">Welcome to secure app- home page</div>";
	}
}
