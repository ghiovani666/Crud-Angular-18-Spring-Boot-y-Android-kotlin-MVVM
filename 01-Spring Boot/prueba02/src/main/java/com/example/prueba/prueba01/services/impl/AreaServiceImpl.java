package com.example.prueba.prueba01.services.impl;

import com.example.prueba.prueba01.persistence.entities.AreaEntity;
import com.example.prueba.prueba01.persistence.repositories.AreaRepository;
import com.example.prueba.prueba01.services.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements IAreaService {

    @Autowired
    AreaRepository areaRepository;

    @Override
    public List<AreaEntity> findAllArea(){
        return areaRepository.findAll();
    }
}
