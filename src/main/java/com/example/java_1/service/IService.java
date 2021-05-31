package com.example.java_1.service;

import com.example.java_1.model.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IService<T> {
    Object listar();
    Object insertar(T model);
    Object modificar(T model);
    Object eliminar(Integer id, Integer idusuario);
    Object getByRegistro(T model);
    Object getByDato(Integer id);

}
