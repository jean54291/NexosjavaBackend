package com.example.java_1.rest;


import com.example.java_1.model.Usuario;
import com.example.java_1.repo.IUsuarioRepo;
import com.example.java_1.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/webservices/usuarios")
public class UsuarioController {
    @Qualifier("usuarioServiceImpl")
    @Autowired
    private IService service;

    @GetMapping
    public Object listar(){
    //public ResponseEntity<Object> listar(){
        return service.listar();
    }
    @PostMapping
    public Object insertar(@RequestBody Usuario usu){
        return  service.insertar(usu);
    }
    @PutMapping
    public Object modificar(@RequestBody Usuario usu){
       return service.modificar(usu);
    }
    @DeleteMapping(value = "/{id}/{idusuario}")
    public Object eliminar(@PathVariable("id") Integer id, @PathVariable("idusuario") Integer idusuario){
        return service.eliminar(id,idusuario);
    }
    @GetMapping(value = "/{datosFiltros}")
    public Object getByRegistro(@PathVariable("datosFiltros") Usuario datosFiltros){
        return  service.getByRegistro(datosFiltros);
    }
    @GetMapping(value = "/getDatoId/{datosFiltros}")
    public Object getByDato(@PathVariable("datosFiltros") Integer id){
        return service.getByDato(id);
    }
}
