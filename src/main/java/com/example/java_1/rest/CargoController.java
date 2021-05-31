package com.example.java_1.rest;

import com.example.java_1.exception.Errors;
import com.example.java_1.exception.ExceptionMessage;
import com.example.java_1.model.Cargo;

import com.example.java_1.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/webservices/cargos")
public class CargoController {
    @Qualifier("cargoServiceImpl")
    @Autowired
    private IService services;

    @GetMapping
    public Object listar(){

        try {
            return services.listar();
        } catch (Exception e) {
            return new ResponseEntity(new ExceptionMessage(Errors.ERROR_VALIDAR_DATOS_ENVIADOS.toString(),Errors.ERROR_VALIDAR_DATOS_ENVIADOS.getDescripcion()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PostMapping
    public Object insertar(@RequestBody Cargo cargo){
        return  services.insertar(cargo);
    }
    @PutMapping
    public Object modificar(@RequestBody Cargo cargo){
        return services.modificar(cargo);
    }
    @DeleteMapping(value = "/{id}/{idusuario}")
    public Object eliminar(@PathVariable("id") Integer id, @PathVariable("idusuario") Integer idusuario){
        return services.eliminar(id,idusuario);
    }
    @GetMapping(value = "/{datosFiltros}")
    public Object getByRegistro(@PathVariable("datosFiltros") Cargo datosFiltros){
        return  services.getByRegistro(datosFiltros);
    }
    @GetMapping(value = "/getDatoId/{datosFiltros}")
    public Object getByDato(@PathVariable("datosFiltros") Integer id){
        return  services.getByDato(id);
    }



}
