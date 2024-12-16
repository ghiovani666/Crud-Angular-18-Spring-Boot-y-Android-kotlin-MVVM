package com.example.prueba.prueba01.controllers;

import com.example.prueba.prueba01.persistence.entities.AreaEntity;
import com.example.prueba.prueba01.services.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/area")
public class AreaController {

    @Autowired
    IAreaService areaService;

    /* Listar */
    @GetMapping("/list")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<AreaEntity> getAllArea() {
        return areaService.findAllArea();
    }
}
