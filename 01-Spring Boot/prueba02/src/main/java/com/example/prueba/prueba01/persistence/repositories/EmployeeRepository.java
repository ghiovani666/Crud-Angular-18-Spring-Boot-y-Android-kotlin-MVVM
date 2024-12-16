package com.example.prueba.prueba01.persistence.repositories;

import com.example.prueba.prueba01.persistence.entities.EmployeeEntity;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long> {
    List<EmployeeEntity> findAll();

}
