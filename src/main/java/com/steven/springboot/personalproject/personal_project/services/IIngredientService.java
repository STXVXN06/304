package com.steven.springboot.personalproject.personal_project.services;

import java.util.List;
import java.util.Optional;

import com.steven.springboot.personalproject.personal_project.entities.Ingredient;

public interface IIngredientService {

     List<Ingredient> findAll();

    Optional<Ingredient> findById(Long id);

    Ingredient save(Ingredient ingredient);

    Optional<Ingredient> update(Long id, Ingredient ingredient);

    Optional<Ingredient> delete(Long id);
}
