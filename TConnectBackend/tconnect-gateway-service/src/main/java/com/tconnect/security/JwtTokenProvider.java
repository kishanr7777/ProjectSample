package com.tconnect.security;

import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
	
	Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

	@Value("${security.jwt.token.secret-key}")
	private String secretKey;

	@Value("${security.jwt.token.expire-length}")
	private long validityInMillis;

	@Autowired
	private LoginUserService loginUserService;

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	public Authentication getAuthentication(String token) {

		UserDetails user = loginUserService.loadUserByUsername(getUsername(token));
		return new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
	}

	public String getUsername(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}

	public String CreateToken(String username, Collection<GrantedAuthority> authorities) {
		Claims claims = Jwts.claims().setSubject(username);
		claims.put("auth", authorities.stream().map(p -> p.getAuthority()).collect(Collectors.toList()));

		Date created = new Date();
		Date expired = new Date(created.getTime() + validityInMillis);

		String generatedToken = Jwts.builder().setClaims(claims).setIssuedAt(created).setExpiration(expired)
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();

		return generatedToken;
	}

	public String resolveToken(HttpServletRequest httpServletRequest) {
		String bearerToken = httpServletRequest.getHeader("Authorization");
		
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		
		return null;

	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
		} catch (JwtException | IllegalArgumentException e) {
			logger.info(e.getMessage());
			return false;
		}
		return true;
	}
}
