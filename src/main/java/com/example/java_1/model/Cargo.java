package com.example.java_1.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idCargo")
    private int idCargo;

    @Column
    private String descripcion;


    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
