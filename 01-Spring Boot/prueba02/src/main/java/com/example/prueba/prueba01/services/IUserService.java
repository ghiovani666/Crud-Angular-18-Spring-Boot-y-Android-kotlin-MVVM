package com.example.prueba.prueba01.services;

import com.example.prueba.prueba01.persistence.entities.UserEntity;

import java.util.List;

public interface IUserService {

    public List<UserEntity> findAllUsers();
}
