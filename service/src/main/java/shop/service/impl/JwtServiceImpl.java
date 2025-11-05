package shop.service.impl;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import shop.service.JwtService;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {

    private final SecretKey secretKey = Keys.hmacShaKeyFor(
            "SuperSecretKeyForJwtShouldBeLongEnough123!".getBytes()
    );

    private final long tokenLifetime = 600_000; // 10 минут

    @Override
    public String generateToken(String login) {
        Date now = new Date();
        Date expiresAt = new Date(now.getTime() + tokenLifetime);

        return Jwts.builder()
                .subject(login)
                .issuedAt(now)
                .expiration(expiresAt)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String getLoginFromToken(String token) {
        return Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
