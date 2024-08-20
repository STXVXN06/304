package com.steven.springboot.personalproject.personal_project.services;

import java.util.List;
import java.util.Optional;

import com.steven.springboot.personalproject.personal_project.entities.Product;

public interface IProductService {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Optional<Product> findByIdWithIngredients(Long id);

    Product save(Product product);

    Optional<Product> update(Long id, Product product);

    Optional<Product> delete(Long id);



}
