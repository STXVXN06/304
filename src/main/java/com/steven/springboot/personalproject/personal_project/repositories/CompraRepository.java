package com.steven.springboot.personalproject.personal_project.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.steven.springboot.personalproject.personal_project.entities.Compra;

public interface CompraRepository extends CrudRepository<Compra, Long> {

    @Query("select c FROM Compra c LEFT JOIN FETCH c.ingrediente")
    List<Compra> findAllWithIngredientes();

    @Query("select c FROM Compra c LEFT JOIN FETCH c.ingrediente WHERE c.idCompra = ?1")
    Optional<Compra> findByIdWithIngredientes(Long id);
}
