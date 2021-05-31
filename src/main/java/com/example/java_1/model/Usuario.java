package com.example.java_1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idUsuario")
    @NotNull
    private int idUsuario;

    @Column(name = "nombre", length = 255)
    private String nombre;

    @Column(name = "edad", nullable = false)
    private String edad;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "fecha_ingreso")
    private Date fecha_ingreso;


    @ManyToOne
    @JoinColumn(name="idCargo")
    private Cargo cargo;


    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getEdad() { return edad; }

    public void setEdad(String edad) { this.edad = edad; }
}
