package com.example.java_1.repo;

import com.example.java_1.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUsuarioRepo extends JpaRepository<Usuario, Integer> {





    @Query(value = "SELECT * FROM usuario where id_usuario = :id",nativeQuery = true)
    List<Usuario> usuariosById(@Param("id") Integer id);

    Usuario findByNombre(String nombre);

    //Cargo findByCargos(String id);



}
