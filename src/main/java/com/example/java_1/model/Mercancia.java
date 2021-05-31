package com.example.java_1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import io.micrometer.core.lang.Nullable;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Mercancia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idMercancia")
    private int idMercancia;

    @Column(name = "nombre")
    private String nombre;

    @JsonProperty
    @Column(name = "cantidad")
    private int cantidad;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "fecha_ingreso")
    private Date fecha_ingreso;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "fecha_modificacion")
    private Date fecha_modificacion;

    @ManyToOne
    @JoinColumn(name="idUsuario")
    private Usuario usuario_asignado;


    @ManyToOne
    @JoinColumn(name="last_user")
    private Usuario last_user;

    public Usuario getLast_user() {
        return last_user;
    }

    public void setLast_user(Usuario last_user) {
        this.last_user = last_user;
    }

    public Usuario getUsuario_asignado() {
        return usuario_asignado;
    }

    public void setUsuario_asignado(Usuario usuario_asignado) {
        this.usuario_asignado = usuario_asignado;
    }



    public int getIdMercancia() {
        return idMercancia;
    }

    public void setIdMercancia(int idMercancia) {
        this.idMercancia = idMercancia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public Date getFecha_modificacion() {
        return fecha_modificacion;
    }

    public void setFecha_modificacion(Date fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }
}
