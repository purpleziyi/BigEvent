package cc.ziyiz.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

// usually companies or projects may choose to provide their own encapsulated utility classes or libraries
// here refer to some commonly used templates from the Internet
public class JwtUtil {

    private static final String KEY = "ziyiz";
	
	// Receive business data, generate token and return
    // Encapsulate business data using Map type
    public static String genToken(Map<String, Object> claims) {
        return JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))
                .sign(Algorithm.HMAC256(KEY));
    }

	// Receive token, verify token, and return business data
    public static Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(KEY))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }

}
