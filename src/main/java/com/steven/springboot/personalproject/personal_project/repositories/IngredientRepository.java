package com.steven.springboot.personalproject.personal_project.repositories;

import org.springframework.data.repository.CrudRepository;

import com.steven.springboot.personalproject.personal_project.entities.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

}
