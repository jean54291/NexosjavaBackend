package com.example.java_1.service.impl;


import com.example.java_1.exception.Errors;
import com.example.java_1.exception.ExceptionMessage;
import com.example.java_1.model.Cargo;

import com.example.java_1.model.Mercancia;
import com.example.java_1.model.Usuario;
import com.example.java_1.repo.ICargoRepo;
import com.example.java_1.repo.IUsuarioRepo;

import com.example.java_1.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoServiceImpl implements IService<Cargo> {
    @Autowired
    private ICargoRepo repo;
    @Autowired
    private IUsuarioRepo usuRepo;

    @Override
    public Object listar() {

        try{
            List<Cargo> cargos = repo.findAll();
            if(cargos.size()==0) {
                return new ResponseEntity(new ExceptionMessage(Errors.CARGOS_VACIO.toString(),Errors.CARGOS_VACIO.getDescripcion()),HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return cargos;
        }catch (Exception e){
            return new ResponseEntity(new ExceptionMessage(Errors.ERROR_VALIDAR_DATOS_ENVIADOS.toString(),Errors.ERROR_VALIDAR_DATOS_ENVIADOS.getDescripcion()), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @Override
    public Object insertar(Cargo carg) {
        try{
            return repo.save(carg);
        }catch (Exception e){
            return new ResponseEntity(new ExceptionMessage(Errors.ERROR_VALIDAR_DATOS_ENVIADOS.toString(),Errors.ERROR_VALIDAR_DATOS_ENVIADOS.getDescripcion()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Object modificar(Cargo carg) {
        try{
            return repo.save(carg);
        }catch (Exception e){
            return new ResponseEntity(new ExceptionMessage(Errors.ERROR_VALIDAR_DATOS_ENVIADOS.toString(),Errors.ERROR_VALIDAR_DATOS_ENVIADOS.getDescripcion()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Object eliminar(Integer id, Integer idusuario) {
        try{
            Usuario user = usuRepo.getById(idusuario);
            Cargo car = repo.getById(id);
            System.out.println (user);
            repo.deleteById(id);
        }catch (Exception e){
            return new ResponseEntity(new ExceptionMessage(Errors.ERROR_VALIDAR_DATOS_ENVIADOS.toString(),Errors.ERROR_VALIDAR_DATOS_ENVIADOS.getDescripcion()), HttpStatus.INTERNAL_SERVER_ERROR);
        }



        return ResponseEntity.ok(true);


    }

    @Override
    public Object getByRegistro(Cargo datosFiltros) {
        try{
            Cargo cargos = repo.findByDescripcion(datosFiltros.getDescripcion());
            return ResponseEntity.ok(cargos);
        }catch (Exception e){
            return new ResponseEntity(new ExceptionMessage(Errors.ERROR_VALIDAR_DATOS_ENVIADOS.toString(),Errors.ERROR_VALIDAR_DATOS_ENVIADOS.getDescripcion()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public Object getByDato(Integer id) {
        try{
            List<Cargo> car = repo.findByID(id);
            return car;
        }catch (Exception e){
            return new ResponseEntity(new ExceptionMessage(Errors.ERROR_VALIDAR_DATOS_ENVIADOS.toString(),Errors.ERROR_VALIDAR_DATOS_ENVIADOS.getDescripcion()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
