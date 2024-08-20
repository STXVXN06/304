package com.steven.springboot.personalproject.personal_project.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.steven.springboot.personalproject.personal_project.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
    
       @Query("select p FROM Product p LEFT JOIN FETCH p.ingredientes")
    List<Product> findAllWithIngredientesAndReceta();

    @Query("select p FROM Product p LEFT JOIN FETCH p.ingredientes WHERE p.idProducto = ?1")
    Optional<Product> findByIdWithIngredientes(Long id);

}
