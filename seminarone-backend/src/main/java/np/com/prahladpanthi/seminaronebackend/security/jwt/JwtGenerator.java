package np.com.prahladpanthi.seminaronebackend.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtGenerator {

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    private static final long JWT_EXPIRATION = 12 * 60 * 60; //12 hours

    public String generateJwtToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + JWT_EXPIRATION);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        log.info("JWT Token: " + token);

        return token;
    }

    public String getUsernameFromJwt(String token) {
        return ""; // need to complete this
    }
}
