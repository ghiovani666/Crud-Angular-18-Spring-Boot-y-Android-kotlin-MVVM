package com.example.prueba.prueba01.controllers;

import com.example.prueba.prueba01.persistence.entities.EmployeeEntity;
import com.example.prueba.prueba01.services.IEmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    /* Listar */
    @GetMapping("/list")
     @CrossOrigin(origins = "http://localhost:4200")
    public List<EmployeeEntity> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    /* Editar */
    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable(value = "id") Long employeeId) {
        Optional<EmployeeEntity> employee = employeeService.getEmployeeById(employeeId);
        if (employee.isPresent()) {
            return ResponseEntity.ok().body(employee.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /* Crear */
    @PostMapping("/create")
    @CrossOrigin(origins = "http://localhost:4200")
    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity employee) {
        return employeeService.createEmployee(employee);
    }

    /* Actualizar */
    @PutMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<EmployeeEntity> updateEmployee(@PathVariable(value = "id") Long employeeId,
            @RequestBody EmployeeEntity employeeDetails) {
        Optional<EmployeeEntity> optionalEmployee = employeeService.updateEmployee(employeeId, employeeDetails);
        if (optionalEmployee.isPresent()) {
            return ResponseEntity.ok(optionalEmployee.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /* Eliminar */
    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    // @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<JsonNode> deleteEmployee(@PathVariable("id") Long employeeId) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        boolean isDeleted = employeeService.deleteEmployee(employeeId);
        if (isDeleted) {
            JsonNode json = mapper.readTree("{ \"mensaje\": \"Employee has been deleted successfully\"}");
            return new ResponseEntity<JsonNode>(json, HttpStatus.OK);
        } else {
            JsonNode json = mapper.readTree("{ \"mensaje\": \"Employee has been deleted successfully\"}");

            return new ResponseEntity<JsonNode>(json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}