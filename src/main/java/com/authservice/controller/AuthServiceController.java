package com.authservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.authservice.models.Employee;
import com.authservice.service.JWTService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;


@CrossOrigin
@Controller
@RequestMapping(path="/auth")
public class AuthServiceController{
    private final static Logger loggerAuth = LoggerFactory.getLogger(AuthServiceController.class);
    

    @Autowired
    private JWTService jwtService;

    @PostMapping(path="/create")
    public ResponseEntity<?> createJWT(@RequestBody Employee emp) {

        loggerAuth.info("Request to create a JWT");
        emp.setPassword("hidden");

        try {
            String jwt = jwtService.createJWT(emp);
            return ResponseEntity.ok().body(jwt);
        }
        catch (Exception e){
            loggerAuth.error("Error creating jwt", e);
            return ResponseEntity.internalServerError().build();
        }

    }
    @PostMapping(path="/verify")
    public ResponseEntity<?> verifyJWT(@RequestBody String JWT) {
        loggerAuth.info("Request to verify JWT");

        try {
            Employee employee = jwtService.parseJwtToEmployee(JWT);
            String newJwt = jwtService.createJWT(employee);
            return ResponseEntity.status(HttpStatus.OK).header("token", newJwt).body(employee);
            //return ResponseEntity.accepted().header("token", newJwt).body(employee);
        }
        catch (SignatureException sex){
            loggerAuth.error("Unauthorized due to bad signature", sex);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("SignatureException");
        }
        catch (ExpiredJwtException ejx){
            loggerAuth.error("EXPIRED", ejx);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Expired");
        }
        catch (MalformedJwtException mje){
            loggerAuth.error("Malformed JWT", mje);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Malformed JWT");
        }
        catch (Exception e){
            loggerAuth.error("error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("I dont know lol");
        }  
        
    }
    
}
