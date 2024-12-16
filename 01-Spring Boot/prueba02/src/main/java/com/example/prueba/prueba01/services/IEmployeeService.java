package com.example.prueba.prueba01.services;

import com.example.prueba.prueba01.persistence.entities.EmployeeEntity;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {

    public List<EmployeeEntity> getAllEmployees();
	Optional<EmployeeEntity> getEmployeeById(Long employeeId);
	EmployeeEntity createEmployee(EmployeeEntity employee);
	Optional<EmployeeEntity> updateEmployee(Long employeeId,EmployeeEntity employee);
	boolean deleteEmployee(Long employeeId);

}
