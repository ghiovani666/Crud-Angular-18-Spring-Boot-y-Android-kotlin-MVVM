package com.example.prueba.prueba01.services;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
//import io.jsonwebtoken.Claims;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;

public interface IJWTUtilityService {

    public String generateJWT(Long subject) throws JOSEException, ParseException, JOSEException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, JOSEException;

    public JWTClaimsSet parseJWT(String jwt) throws ParseException, JOSEException, IOException, NoSuchAlgorithmException, InvalidKeySpecException;

}
