package rithik.hotel_management_system.security;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    private SecretKey getSigningKey() {

        byte[] keyBytes = Decoders.BASE64.decode(secret);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(String email) {

        return Jwts.builder()
                .subject(email)
                .signWith(getSigningKey())
                .compact();
    }
    
    public String extractEmail(String token) {
    	return Jwts.parser()
    			.verifyWith(getSigningKey())
    			.build()
    			.parseSignedClaims(token)
    			.getPayload()
    			.getSubject();
    			}
}