package com.sem4project.sem4.util;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
@Component
@Slf4j
public class JwtUtil implements Serializable {
    @Serial
    private static final long serialVersionUID = 420851166684154253L;
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    //life-time of jwt
    public static long JWT_EXPIRATION = 10 * 24 * 60 * 60;
    //secret key get from application.properties
    @Value("${jwt.secret}")
    private String jwtSecret;

    //like the name
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //like the name
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    //get a specific claim from token
    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //get all claims from token
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    //check token validity
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            logger.error("Token invalid");
        } catch (ExpiredJwtException ex) {
            logger.error("Token expired");
        } catch (UnsupportedJwtException ex) {
            logger.error("Token unsupported");
        } catch (IllegalArgumentException ex) {
            logger.error("Token's claim is empty");
        }
        return false;
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails);
    }

    private String doGenerateToken(Map<String, Object> claims, UserDetails userDetails) {
        //generate a jwt
        //subject: object of token(here is username)
        //issuedAt: token creation date
        //expiration: token expiration date
        //signWith: first is sign algorithm (HS512), second is secret key
        return Jwts.builder().setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}
