package com.springsecurity.securityjwt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    private static final String SECRET_KEY="123456";
    private static final int TOKEN_VALIDITY=3600*5;


    public String getUserNmeFromToken(String token){
        return getClaimsFromToken(token, Claims::getSubject);

    }

    private <T> T getClaimsFromToken(String token, Function<Claims,T> claimResolver) {
        final Claims claims=getAllClaimsFromToken(token);
        return claimResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJwt(token).getBody();
    }

    public Boolean validateToken(String token, UserDetails details){
        final String userName=getUserNmeFromToken(token);
        return  (userName.equals(details.getUsername()) && !isTokenExpired(token));

    }

    private boolean isTokenExpired(String token) {
        final Date expireDate= getClaimsFromToken(token,Claims::getExpiration);
        return expireDate.before(new Date());
    }

    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims= new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+TOKEN_VALIDITY*1000))
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY)
                .compact();

    }


}
