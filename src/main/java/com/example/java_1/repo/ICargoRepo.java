package com.example.java_1.repo;

import com.example.java_1.model.Cargo;
import com.example.java_1.model.Mercancia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ICargoRepo extends JpaRepository<Cargo, Integer> {

    Cargo findByDescripcion(String descripcion);
    @Query(value = "SELECT * FROM cargo where id_cargo = :id",nativeQuery = true)
    List<Cargo> findByID(@Param("id") Integer idCargo);


}
