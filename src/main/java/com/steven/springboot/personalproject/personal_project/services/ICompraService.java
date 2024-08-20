package com.steven.springboot.personalproject.personal_project.services;

import java.util.List;
import java.util.Optional;

import com.steven.springboot.personalproject.personal_project.entities.Compra;

public interface ICompraService {
     List<Compra> findAll();

    Optional<Compra> findById(Long id);

    Optional<Compra> findByIdWithIngredients(Long id);

    Compra save(Compra compra);

    Optional<Compra> update(Long id, Compra compra);

    Optional<Compra> delete(Long id);
}
