package com.example.prueba.prueba01.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.prueba.prueba01.persistence.entities.AreaEntity;

import java.util.List;

public interface AreaRepository extends CrudRepository<AreaEntity, Long> {
    List<AreaEntity> findAll();
}
