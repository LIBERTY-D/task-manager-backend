package com.daniel.app.TaskManager.user.service;


import com.daniel.app.TaskManager.user.config.JwtConfig;
import com.daniel.app.TaskManager.user.model.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private final JwtConfig jwtConfig;

    public JwtService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public  String getUserName(String token) {

        return  extractClaim(token, Claims::getSubject);
    }

    public  boolean isValid(String token, UserDetails user){
        if(user == null){
            return false;
        }
        String username =  getUserName(token);
        return  (username.equals(user.getUsername())) && !isTokenExpired(token);

    }
    private  boolean isTokenExpired(String token){
        Date exp =  extractClaim(token,Claims::getExpiration);
        return  exp.before(new Date());
    }
    public  <T> T extractClaim(String token , Function<Claims,T> resolver ){
        Claims  claims  = extractAllClaims(token);
        return  resolver.apply(claims);
    }


    // returns all the claims
    public Claims extractAllClaims(String token){

        return  Jwts.parser().
                verifyWith(getSigningKey()).build().
                parseSignedClaims(token).getPayload();
    }
    // generate token and return it
    public  String generateToken(UserModel user){
        Date issue = new Date(System.currentTimeMillis());
        Date exp =
                new Date(System.currentTimeMillis()+ jwtConfig.getJwt_expires());

        return Jwts.builder().subject(user.getUsername()).
                issuedAt(issue).expiration(exp).
                signWith(getSigningKey()).compact();
    }
    // getSigningKey
    private SecretKey getSigningKey() {
        byte [] keyBytes = Decoders.BASE64URL.decode(jwtConfig.getJwt_secret());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
