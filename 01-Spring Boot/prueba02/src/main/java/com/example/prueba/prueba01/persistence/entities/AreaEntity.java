package com.example.prueba.prueba01.persistence.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "area")
public class AreaEntity {

    // public AreaEntity() {} // Si ya tienen lumbok no tiene sentido en declaralo
    //   @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    
    @Column(name = "names",insertable=false, updatable=false)
    private String names;

   
    // @OneToMany(fetch = FetchType.LAZY, mappedBy = "area")
    // private List<EmployeeEntity> empleados;
}
