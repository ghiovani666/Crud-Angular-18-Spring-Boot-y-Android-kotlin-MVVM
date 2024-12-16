package com.example.prueba.prueba01.services.impl;

import com.example.prueba.prueba01.persistence.entities.UserEntity;
import com.example.prueba.prueba01.persistence.repositories.UserRepository;
import com.example.prueba.prueba01.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserEntity> findAllUsers(){
        return userRepository.findAll();
    }
}
