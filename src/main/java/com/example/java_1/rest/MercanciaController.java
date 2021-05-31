package com.example.java_1.rest;

import com.example.java_1.exception.Errors;
import com.example.java_1.exception.ExceptionMessage;
import com.example.java_1.model.Mercancia;

import com.example.java_1.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/webservices/mercancia")
public class MercanciaController {
    @Qualifier("mercanciaServiceImpl")
    @Autowired
    private IService service;

    @GetMapping
    public Object listar(){
        return service.listar();
    }
    @PostMapping
    public Object insertar(@RequestBody Mercancia merca){
        return  service.insertar(merca);
    }
    @PutMapping
    public Object modificar(@RequestBody Mercancia merca){
        return service.modificar(merca);
    }
    @DeleteMapping(value = "/{id}/{idusuario}")
    public Object eliminar(@PathVariable("id") Integer id, @PathVariable("idusuario") Integer idusuario){
        return service.eliminar(id,idusuario);
    }
    @PostMapping("/listarMercancia")
    public Object getByRegistro(@RequestBody Mercancia merca) {
        return  service.getByRegistro(merca);
    }

    @GetMapping(value = "/getDatoId/{datosFiltros}")
    public Object getByDato(@PathVariable("datosFiltros") Integer id){
        return service.getByDato(id);
    }
}
