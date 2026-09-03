package com.moutii.TheaterHub.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    private final String TOKEN_TYPE = "token_type";
    private final PrivateKey privateKey;
    private final PublicKey publicKey;

    @Value("${app.security.jwt.access-token-expiration}")
    private long accessTokenExpiration;
    @Value("${app.security.jwt.refesh-token-expiration}")
    private long refreshTokenExpiration;

    JwtService() throws Exception {
        privateKey = KeyUtils.getPrivateKey();
        publicKey = KeyUtils.getPublicKey();
    }

    public String generateAccessToken(final String username) {
        Map<String,Object> claims = Map.of(TOKEN_TYPE,"ACCESS_TOKEN");
        return buildToken(username,accessTokenExpiration,claims);
    }
    public String generateRefreshToken(final String username) {
        Map<String,Object> claims = Map.of(TOKEN_TYPE,"REFRESH_TOKEN");
        return buildToken(username,refreshTokenExpiration,claims);
    }

    private String buildToken(final String username, long expiration, Map<String,Object> claims) {
        return Jwts.builder()
                .claims(claims)
                .signWith(privateKey)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .compact();
    }

    public boolean isTokenValid(final String token,final String username) {
        return extractUsername(token).equals(username) && !isExpired(token);
    }

    public String extractUsername(final String token) {
        return extractClaims(token).getSubject();
    }

    private boolean isExpired(final String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    private Claims extractClaims(final String token) {
        return Jwts.parser()
                .verifyWith(publicKey)
                .build()
                .parseSignedClaims(token).getPayload();
    }

    public String refreshAccessToken(final String refreshToken) {
        Claims claims = extractClaims(refreshToken);
        if(!claims.get(TOKEN_TYPE).equals("REFRESH_TOKEN")) {
            throw new RuntimeException("Invalid token type");
        }
        if(isExpired(refreshToken)) {
            throw new RuntimeException("Refresh Token Expired");
        }
        final String username = claims.getSubject();
        return generateAccessToken(username);
    }

}
