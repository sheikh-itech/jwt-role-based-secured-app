package base.module.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTUtil {


	private String secrete_key="mysecrete";
	//Keep this very secrete, probably in properties file
	
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		
		final Claims claim = extractAllClaims(token);
		return claimResolver.apply(claim);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(secrete_key).parseClaimsJws(token).getBody();
	}
	
	public Boolean isTokenExpired(String token) {
		
		return extractExpiration(token).before(new Date());
	}
	
	public String generateToken(UserDetails userDetail) {
		Map<String, Object> claims = new HashMap<>();
		
		return createToken(claims, userDetail.getUsername());
	}
	
	public String generateTokenUsingUsername(String username) {
		Map<String, Object> claims = new HashMap<>();
		
		return createToken(claims, username);
	}

	private String createToken(Map<String, Object> claims, String username) {
		
		return	Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis()+ 1000*60*60*10))
				.signWith(SignatureAlgorithm.HS256, secrete_key).compact();
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		
		final String username = extractUsername(token);
		
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
