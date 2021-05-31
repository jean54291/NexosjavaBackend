package com.example.java_1.repo;

import com.example.java_1.model.Cargo;
import com.example.java_1.model.Mercancia;
import com.example.java_1.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


public interface IMercanciaRepo extends JpaRepository<Mercancia, Integer> {
    @Query(value = "SELECT * FROM mercancia where id_usuario = :id and nombre like :nombre",nativeQuery = true)
    List<Mercancia> findByUsuario_asignadoAndNombre(
            @Param("id") Integer usuario_asignado,
            @Param("nombre") String nombre);
    @Query(value = "SELECT * FROM mercancia where id_usuario = :id",nativeQuery = true)
    List<Mercancia> findByUsuario_asignado( @Param("id") Integer usuario_asignado);
    @Query(value = "SELECT * FROM mercancia where id_usuario = :id and nombre like :nombre and fecha_ingreso = :fecha",nativeQuery = true)
    List<Mercancia> findByFecha_ingresoAndUsuario_asignadoAndNombre(
            @Param("fecha") Date fecha_ingreso,
            @Param("id") Integer usuario_asignado,
            @Param("nombre") String nombre);
    @Query(value = "SELECT * FROM mercancia where fecha_ingreso = :fecha",nativeQuery = true)
    List<Mercancia> findByFecha_ingreso(@Param("fecha") Date fecha_ingreso);
    @Query(value = "SELECT * FROM mercancia where id_usuario = :id and fecha_ingreso = :fecha",nativeQuery = true)
    List<Mercancia> findByFecha_ingresoAndUsuario_asignado(
            @Param("fecha") Date fecha_ingreso,
            @Param("id") Integer usuario_asignado
            );
    @Query(value = "SELECT * FROM mercancia where nombre like :nombre and fecha_ingreso = :fecha",nativeQuery = true)
    List<Mercancia> findByFecha_ingresoAndNombre(
            @Param("fecha") Date fecha_ingreso,
            @Param("nombre") String nombre);
    Mercancia findByNombre(String nombre);
    @Query(value = "SELECT * FROM mercancia where id_mercancia = :id",nativeQuery = true)
    List<Mercancia> findByID(@Param("id") Integer idMercancia);
    @Query(value = "SELECT * FROM mercancia where nombre like :nombre",nativeQuery = true)
    List<Mercancia> findByName( @Param("nombre") String nombre);




}
