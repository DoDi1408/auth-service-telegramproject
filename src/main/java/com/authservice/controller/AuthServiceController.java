package com.authservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.authservice.models.Employee;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;

import javax.crypto.SecretKey;

@CrossOrigin
@Controller
@RequestMapping(path="/auth")
public class AuthServiceController{
    private final static Logger loggerAuth = LoggerFactory.getLogger(AuthServiceController.class);
    
    private final SecretKey pvKey = Jwts.SIG.HS512.key().build();

    @PostMapping(path="/create")
    public ResponseEntity<?> createJWT(@RequestBody Employee emp) {

        loggerAuth.info("Request to create a JWT");

        Date currentTime = new Date();
        Date expirationTime = new Date(currentTime.getTime() + TimeUnit.HOURS.toMillis(3600000));
        emp.setPassword("hidden");
        try {
            String jwt = Jwts.builder()
                .issuer("auth-service")
                .subject("EmployeeInfo")
                .claim("id",emp.getId())
                .claim("firstName",emp.getFirstName())
                .claim("lastName",emp.getLastName())
                .claim("telegramId",emp.getTelegramId())
                .claim("password",emp.getPassword())
                .claim("team",emp.getTeam())
                .claim("email",emp.getEmail())
                .expiration(expirationTime)
                .issuedAt(currentTime)
                .signWith(pvKey)
                .compact();
            return ResponseEntity.ok().body(jwt);
        }
        catch (Exception e){
            loggerAuth.error(null, e);
            return ResponseEntity.internalServerError().build();
        }

    }
    @PostMapping(path="/verify")
    public ResponseEntity<?> verifyJWT(@RequestBody String JWT) {
        loggerAuth.info("Request to verify JWT");
        Jws<Claims> parsedJwt;
        try {
            parsedJwt = Jwts.parser()
                .verifyWith(pvKey)
                .build()
                .parseSignedClaims(JWT);
            Claims claims = parsedJwt.getPayload();

            Employee returnEmployee = new Employee(claims.get("firstName", String.class),claims.get("lastName", String.class),claims.get("telegramId", Integer.class));
            returnEmployee.setEmail(claims.get("email",String.class));
            returnEmployee.setId(claims.get("id",Integer.class));
            returnEmployee.setTeam(claims.get("team",Integer.class));
            returnEmployee.setPassword(claims.get("password",String.class));
            return ResponseEntity.accepted().body(returnEmployee);
        }
        catch (SignatureException sex){
            loggerAuth.error("FORBIDDEN", sex);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch (ExpiredJwtException ejx){
            loggerAuth.error("EXPIRED", ejx);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch (MalformedJwtException mje){
            loggerAuth.error("Malformed JWT", mje);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        catch (JwtException jwtex){
            loggerAuth.error("error", jwtex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }  
    }
    
}
