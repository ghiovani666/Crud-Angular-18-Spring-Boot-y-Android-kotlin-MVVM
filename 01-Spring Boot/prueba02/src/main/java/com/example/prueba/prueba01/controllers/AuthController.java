package com.example.prueba.prueba01.controllers;

import com.example.prueba.prueba01.services.models.dtos.LoginDTO;
import com.example.prueba.prueba01.persistence.entities.UserEntity;
import com.example.prueba.prueba01.services.IAuthService;
import com.example.prueba.prueba01.services.models.dtos.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/register")
    private ResponseEntity<ResponseDTO> addUser(@RequestBody UserEntity user) throws Exception {
        return new ResponseEntity<>(authService.register(user), HttpStatus.OK);
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:4200")
    private ResponseEntity<HashMap<String, String>> login(@RequestBody LoginDTO loginRequest) throws Exception {
        HashMap<String, String> login = authService.login(loginRequest);
        if (login.containsKey("jwt")) {
            return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/refresh")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> auth(@RequestBody Map<String, String>  request) {
        String refreshToken = request.get("refreshToken");

        // if (login.containsKey("jwt")) {
        //     return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.ACCEPTED);
        // } else {
        //     return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.UNAUTHORIZED);
        // }
        return ResponseEntity.ok(null);
    }
 
}
