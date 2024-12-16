package com.example.prueba.prueba01.services;

import com.example.prueba.prueba01.services.models.dtos.LoginDTO;
import com.example.prueba.prueba01.persistence.entities.UserEntity;
import com.example.prueba.prueba01.services.models.dtos.ResponseDTO;

import java.util.HashMap;

public interface IAuthService {

    public HashMap<String, String> login(LoginDTO loginRequest) throws Exception;
    public ResponseDTO register(UserEntity user) throws Exception;
}
