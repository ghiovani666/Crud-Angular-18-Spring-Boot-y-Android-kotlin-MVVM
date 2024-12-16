package com.example.prueba.prueba01.services;

import com.example.prueba.prueba01.persistence.entities.AreaEntity;

import java.util.List;

public interface IAreaService {

    public List<AreaEntity> findAllArea();
}
