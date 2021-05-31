package com.example.java_1.exception;

public class ExceptionMessage {

    private final String codigo;
    private final String descripcion;

    public ExceptionMessage(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
