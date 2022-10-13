package base.module.beans;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class UserDetail implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int userId;
	@Column(unique=true, nullable=false, updatable=false)
	private String username;
	private String password;
	private boolean isEnabled = true;
	private boolean isAccountExpired;
	private boolean isAccountLocked;
	private boolean isCredentialsExpired;
	private String roles;
	@Transient
	private List<GrantedAuthority> authority;
	
	
	public UserDetail() { }
	
	public UserDetail(String username, String password, String roles, boolean isEnabled) {
		this.username = username;
		this.password = password;
		this.isEnabled = isEnabled;
		if(roles!=null && !roles.isBlank())
			this.authority = Arrays.asList(roles.split(",")).stream().map(u->"ROLE_"+u.toUpperCase().trim()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		this.roles = roles;
	}
	
	public UserDetail(String username, String password, String roles, boolean isEnabled,
			boolean isAccountExpired, boolean isAccountLocked, boolean isCredentialsExpired) {
		this.username = username;
		this.password = password;
		this.isEnabled = isEnabled;
		if(roles!=null && !roles.isBlank())
			this.authority = Arrays.asList(roles.split(",")).stream().map(u->"ROLE_"+u.toUpperCase().trim()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		
		this.roles = roles;
		this.isAccountExpired = isAccountExpired;
		this.isAccountLocked = isAccountLocked;
		this.isCredentialsExpired = isCredentialsExpired;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(authority==null || authority.isEmpty())
			if(roles!=null && !roles.isBlank())
				this.authority = Arrays.asList(roles.split(",")).stream().map(u->"ROLE_"+u.toUpperCase().trim()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		return this.authority;
	}

	public void setAuthority(List<GrantedAuthority> authority) {
		this.authority = authority;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !isAccountExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !isAccountLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !isCredentialsExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public boolean isAccountExpired() {
		return isAccountExpired;
	}

	public void setAccountExpired(boolean isAccountExpired) {
		this.isAccountExpired = isAccountExpired;
	}

	public boolean isAccountLocked() {
		return isAccountLocked;
	}

	public void setAccountLocked(boolean isAccountLocked) {
		this.isAccountLocked = isAccountLocked;
	}

	public boolean isCredentialsExpired() {
		return isCredentialsExpired;
	}

	public void setCredentialsExpired(boolean isCredentialsExpired) {
		this.isCredentialsExpired = isCredentialsExpired;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
}
