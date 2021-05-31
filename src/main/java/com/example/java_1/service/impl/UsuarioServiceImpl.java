package com.example.java_1.service.impl;

import com.example.java_1.exception.Errors;
import com.example.java_1.exception.ExceptionMessage;
import com.example.java_1.model.Cargo;
import com.example.java_1.model.Mercancia;
import com.example.java_1.model.Usuario;
import com.example.java_1.repo.IUsuarioRepo;
import com.example.java_1.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements IService<Usuario> {
    @Autowired
    private IUsuarioRepo repo;


    @Override
    public Object listar() {
        try {
            List<Usuario> usuarios = repo.findAll();
            if(usuarios.size()==0) {
                return new ResponseEntity(new ExceptionMessage(Errors.USUARIOS_VACIO.toString(),Errors.USUARIOS_VACIO.getDescripcion()),HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //List<Usuario> usuarios = all.stream().filter(usuario -> !usuario.getNombre().equalsIgnoreCase("natalia")).collect(Collectors.toList());
            return ResponseEntity.ok(usuarios);
        }catch (Exception e){
            return new ResponseEntity(new ExceptionMessage(Errors.ERROR_VALIDAR_DATOS_ENVIADOS.toString(),Errors.ERROR_VALIDAR_DATOS_ENVIADOS.getDescripcion()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Object insertar(Usuario user) {

        try {
            return repo.save(user);
        }catch (Exception e){
            return new ResponseEntity(new ExceptionMessage(Errors.ERROR_VALIDAR_DATOS_ENVIADOS.toString(),Errors.ERROR_VALIDAR_DATOS_ENVIADOS.getDescripcion()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Object modificar(Usuario user) {
        try {
            return repo.save(user);
        }catch (Exception e){
            return new ResponseEntity(new ExceptionMessage(Errors.ERROR_VALIDAR_DATOS_ENVIADOS.toString(),Errors.ERROR_VALIDAR_DATOS_ENVIADOS.getDescripcion()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Object eliminar(Integer id, Integer idusuario) {
        try {
            Usuario user = repo.getById(idusuario);
            repo.deleteById(id);

        }catch (Exception e){
            return new ResponseEntity(new ExceptionMessage(Errors.ERROR_VALIDAR_DATOS_ENVIADOS.toString(),Errors.ERROR_VALIDAR_DATOS_ENVIADOS.getDescripcion()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(true);
    }

    @Override
    public Object getByRegistro(Usuario datosFiltros) {
        try {
            Usuario usuario = repo.findByNombre(datosFiltros.getNombre());
            return usuario;
        }catch (Exception e){
            return new ResponseEntity(new ExceptionMessage(Errors.ERROR_VALIDAR_DATOS_ENVIADOS.toString(),Errors.ERROR_VALIDAR_DATOS_ENVIADOS.getDescripcion()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Object getByDato(Integer id) {
        try {
            List<Usuario> usuario = repo.usuariosById(id);
            return usuario;
        }catch (Exception e){
            return new ResponseEntity(new ExceptionMessage(Errors.ERROR_VALIDAR_DATOS_ENVIADOS.toString(),Errors.ERROR_VALIDAR_DATOS_ENVIADOS.getDescripcion()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
