package com.authservice.service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.authservice.models.Employee;

import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

@Component
public class JWTService {

    private final SecretKey pvKey = Jwts.SIG.HS512.key().build();

    public String createJWT(Employee emp){

        Date currentTime = new Date();
        Date expirationTime = new Date(currentTime.getTime() + TimeUnit.HOURS.toMillis(3600000));

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
        return jwt;
    }

    public Employee parseJwtToEmployee(String jwt){
        Jws<Claims> parsedJwt = Jwts.parser().verifyWith(pvKey).build().parseSignedClaims(jwt);
        Claims claims = parsedJwt.getPayload();
        Employee returnEmployee = new Employee(claims.get("firstName", String.class),claims.get("lastName", String.class),claims.get("telegramId", Integer.class));
        returnEmployee.setEmail(claims.get("email",String.class));
        returnEmployee.setId(claims.get("id",Integer.class));
        returnEmployee.setTeam(claims.get("team",Integer.class));
        returnEmployee.setPassword(claims.get("password",String.class));
        return returnEmployee;
    }
}
