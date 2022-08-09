package com.chorley.app.chorelyapp.utils;

import java.io.Serializable;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtTokenUtil implements Serializable {
	private static final long serialVersionUID = -2550185165626007488L;

	public static final long JWT_ACCESS_TOKEN_VALIDITY = 1 * 60 * 60;
	public static final long JWT_REFRESH_TOKEN_VALIDITY = 24 * 60 * 60 * 14;

	@Value("${secret}")
	private String secret;

	// retrieve username from jwt token
	public String getUsernameFromToken(String token) {
		DecodedJWT decoded = JWT.decode(token);
		return decoded.getSubject();
	}

	// retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token) {
		DecodedJWT decoded = JWT.decode(token);
		return decoded.getExpiresAt();
	}

	// check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	// generate token for user
	public String generateAccessToken(String username) {
		return doGenerateToken(username, JWT_ACCESS_TOKEN_VALIDITY);
	}

	public String generateRefreshToken(String username) {
		return doGenerateToken(username, JWT_REFRESH_TOKEN_VALIDITY);
	}

	// while creating the token -
	// 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
	// 2. Sign the JWT using the HS512 algorithm and secret key.
	// 3. According to JWS Compact
	// Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
	// compaction of the JWT to a URL-safe string
	private String doGenerateToken(String subject, long expiration) {

		return JWT.create().withSubject(subject).withIssuedAt(new Date(System.currentTimeMillis()))
				.withExpiresAt(new Date(System.currentTimeMillis() | expiration)).sign(Algorithm.HMAC256(secret));
	}

	// validate token
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
