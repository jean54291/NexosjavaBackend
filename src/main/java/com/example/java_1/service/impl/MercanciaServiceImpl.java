package com.example.java_1.service.impl;

import com.example.java_1.exception.Errors;
import com.example.java_1.exception.ExceptionMessage;
import com.example.java_1.model.Cargo;
import com.example.java_1.model.Mercancia;
import com.example.java_1.model.Usuario;
import com.example.java_1.repo.IMercanciaRepo;
import com.example.java_1.repo.IUsuarioRepo;

import com.example.java_1.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MercanciaServiceImpl implements IService<Mercancia> {

    @Autowired
    private IMercanciaRepo repo;

    @Autowired
    private IUsuarioRepo usuRepo;

    @Override
    public Object listar() {
        List<Mercancia> mercancias = repo.findAll();
        return ResponseEntity.ok(mercancias);
    }

    @Override
    public Object insertar(Mercancia mercancia) {

        Mercancia merca= repo.findByNombre(mercancia.getNombre());
        if(merca != null){
            return new ResponseEntity(new ExceptionMessage(Errors.MERCANCIA_YA_EXISTE.toString(),Errors.MERCANCIA_YA_EXISTE.getDescripcion()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        System.out.println (mercancia.getCantidad());
        String cantidad = mercancia.getCantidad()+"";

        try{

            if(mercancia.getCantidad()<0){
                return new ResponseEntity(new ExceptionMessage(Errors.CANTIDAD_NUMERO.toString(),Errors.CANTIDAD_NUMERO.getDescripcion()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (NumberFormatException excepcion) {
            return new ResponseEntity(new ExceptionMessage(Errors.CANTIDAD_NUMERO.toString(),Errors.CANTIDAD_NUMERO.getDescripcion()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Date date = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        Date fechaactual = date;
        Date fechaIngreso =mercancia.getFecha_ingreso();
        if(fechaIngreso.after(fechaactual)){
            return new ResponseEntity(new ExceptionMessage(Errors.FECHA_ERROR.toString(),Errors.FECHA_ERROR.getDescripcion()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //mercancia.setFecha_modificacion(date);
        repo.save(mercancia);
        return ResponseEntity.ok(mercancia);
    }

    @Override
    public Object modificar(Mercancia mercancia) {
        try{
            Mercancia merca= repo.findByNombre(mercancia.getNombre());

            if(merca != null && mercancia.getIdMercancia()!=merca.getIdMercancia()){
                return new ResponseEntity(new ExceptionMessage(Errors.MERCANCIA_YA_EXISTE.toString(),Errors.MERCANCIA_YA_EXISTE.getDescripcion()), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            String cantidad = mercancia.getCantidad()+"";


            if(mercancia.getCantidad()<0){
                return new ResponseEntity(new ExceptionMessage(Errors.CANTIDAD_NUMERO.toString(),Errors.CANTIDAD_NUMERO.getDescripcion()), HttpStatus.INTERNAL_SERVER_ERROR);
            }


            Date date = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

            Date fechaactual = date;
            Date fechaIngreso =mercancia.getFecha_ingreso();
            if(fechaIngreso.after(fechaactual)){
                return new ResponseEntity(new ExceptionMessage(Errors.FECHA_ERROR.toString(),Errors.FECHA_ERROR.getDescripcion()), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            mercancia.setLast_user(mercancia.getUsuario_asignado());


            mercancia.setFecha_modificacion(date);
            repo.save(mercancia);
            return ResponseEntity.ok(mercancia);
        } catch (Exception e) {

            return new ResponseEntity(new ExceptionMessage(Errors.CANTIDAD_NUMERO.toString(),Errors.CANTIDAD_NUMERO.getDescripcion()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Object eliminar(Integer id, Integer idusuario) {
        Usuario user = usuRepo.getById(idusuario);
        Mercancia merca = repo.getById(id);
        try {
            if(user!=null && user.getIdUsuario()==merca.getUsuario_asignado().getIdUsuario()){
                repo.deleteById(id);
            }else{
                return new ResponseEntity(new ExceptionMessage(Errors.ERROR_PERMISOS_USUARIOS.toString(),Errors.ERROR_PERMISOS_USUARIOS.getDescripcion()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            return new ResponseEntity(new ExceptionMessage(Errors.ERROR_VALIDAR_DATOS_ENVIADOS.toString(),Errors.ERROR_VALIDAR_DATOS_ENVIADOS.getDescripcion()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(true);

    }

    @Override
    public Object getByRegistro(Mercancia datosFiltros) {
        try {
            Mercancia merca = new Mercancia();
            List<Mercancia> mercancias = new ArrayList<Mercancia>() ;
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            if(datosFiltros.getFecha_ingreso()==null && datosFiltros.getNombre()==null && datosFiltros.getUsuario_asignado()==null){
                return new ResponseEntity(new ExceptionMessage(Errors.NO_TIENE_FILTROS_LA_CONSULTA.toString(),Errors.NO_TIENE_FILTROS_LA_CONSULTA.getDescripcion()), HttpStatus.INTERNAL_SERVER_ERROR);
            }else if(datosFiltros.getFecha_ingreso()!=null && datosFiltros.getNombre()!=null && datosFiltros.getUsuario_asignado()!=null){
                Date fechaz =  datosFiltros.getFecha_ingreso();
                mercancias= repo.findByFecha_ingresoAndUsuario_asignadoAndNombre(fechaz,datosFiltros.getUsuario_asignado().getIdUsuario(),datosFiltros.getNombre());

            }else if(datosFiltros.getFecha_ingreso()!=null && datosFiltros.getNombre()!=null && datosFiltros.getUsuario_asignado()==null){
                mercancias= repo.findByFecha_ingresoAndNombre(datosFiltros.getFecha_ingreso(),datosFiltros.getNombre());
            }else if(datosFiltros.getFecha_ingreso()!=null && datosFiltros.getNombre()==null && datosFiltros.getUsuario_asignado()!=null){

                mercancias= repo.findByFecha_ingresoAndUsuario_asignado(datosFiltros.getFecha_ingreso(),datosFiltros.getUsuario_asignado().getIdUsuario());

            }else if(datosFiltros.getFecha_ingreso()==null && datosFiltros.getNombre()!=null && datosFiltros.getUsuario_asignado()!=null){
                mercancias= repo.findByUsuario_asignadoAndNombre(datosFiltros.getUsuario_asignado().getIdUsuario(),datosFiltros.getNombre());

            }else if(datosFiltros.getFecha_ingreso()==null && datosFiltros.getNombre()==null && datosFiltros.getUsuario_asignado()!=null){
                mercancias= repo.findByUsuario_asignado(datosFiltros.getUsuario_asignado().getIdUsuario());
            }else if(datosFiltros.getFecha_ingreso()!=null && datosFiltros.getNombre()==null && datosFiltros.getUsuario_asignado()==null){
                Date fechazw = datosFiltros.getFecha_ingreso();
                mercancias= repo.findByFecha_ingreso(fechazw);

            }else if(datosFiltros.getFecha_ingreso()==null && datosFiltros.getNombre()!=null && datosFiltros.getUsuario_asignado()==null){
                return  ResponseEntity.ok(mercancias= repo.findByName(datosFiltros.getNombre()));
            }

            return ResponseEntity.ok(mercancias);
        }catch (Exception e){
            return new ResponseEntity(new ExceptionMessage(Errors.ERROR_VALIDAR_DATOS_ENVIADOS.toString(),Errors.ERROR_VALIDAR_DATOS_ENVIADOS.getDescripcion()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Object getByDato(Integer id) {
        try {
            List<Mercancia> mercancia = repo.findByID(id);
            return mercancia;
        }catch (Exception e){
            return new ResponseEntity(new ExceptionMessage(Errors.ERROR_VALIDAR_DATOS_ENVIADOS.toString(),Errors.ERROR_VALIDAR_DATOS_ENVIADOS.getDescripcion()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
