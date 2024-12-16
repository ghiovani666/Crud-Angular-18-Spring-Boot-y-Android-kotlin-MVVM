package com.example.prueba.prueba01.services.impl;

import com.example.prueba.prueba01.persistence.entities.AreaEntity;
import com.example.prueba.prueba01.persistence.entities.EmployeeEntity;
import com.example.prueba.prueba01.persistence.repositories.EmployeeRepository;
import com.example.prueba.prueba01.services.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeEntity> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<EmployeeEntity> getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    @Override
    public EmployeeEntity createEmployee(EmployeeEntity employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<EmployeeEntity> updateEmployee(Long employeeId, EmployeeEntity employeeDetails) {
        // .orElseThrow(() -> new MeetingDoesNotExistException(0))
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isPresent()) {
            EmployeeEntity employee = optionalEmployee.get();
            employee.setNames(employeeDetails.getNames());
            employee.setEmail(employeeDetails.getEmail());
            employee.setPhone(employeeDetails.getPhone());
            employee.setArea(new AreaEntity(employeeDetails.getArea().getId(), null));

            return Optional.of(employeeRepository.save(employee));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteEmployee(Long employeeId) {

        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isPresent()) {
            // employeeRepository.delete(optionalEmployee.get());//Cualquier de estas
            // opciones
            employeeRepository.deleteById(employeeId);
            return true;
        } else {
            return false;
        }

    }
}
