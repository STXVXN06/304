package com.steven.springboot.personalproject.personal_project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.steven.springboot.personalproject.personal_project.entities.Ingredient;
import com.steven.springboot.personalproject.personal_project.repositories.IngredientRepository;

@Service
public class IngredientService implements IIngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Ingredient> findAll() {
        return (List<Ingredient>) ingredientRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Ingredient> findById(Long id) {
        return ingredientRepository.findById(id);
    }

    @Transactional
    @Override
    public Ingredient save(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Transactional
    @Override
    public Optional<Ingredient> update(Long id, Ingredient ingredient) {
        Optional<Ingredient> ingredientOptional = ingredientRepository.findById(id);
        if (ingredientOptional.isPresent()) {
            Ingredient ingred = ingredientOptional.orElseThrow();
            ingred.setNombreIngrediente(ingredient.getNombreIngrediente());
            ingred.setUnidadMedida(ingredient.getUnidadMedida());
            ingred.setCantidadActual(ingredient.getCantidadActual());
            ingred.setCantidadMinima(ingredient.getCantidadMinima());
            ingred.setCosto(ingredient.getCosto());
            return Optional.of(ingredientRepository.save(ingred));
        }

        return ingredientOptional;
    }

    @Transactional
    @Override
    public Optional<Ingredient> delete(Long id) {
        Optional<Ingredient> ingredientOptional = ingredientRepository.findById(id);
        ingredientOptional.ifPresent(ingred -> {
            ingredientRepository.delete(ingred);
        });
        return ingredientOptional;
    }

}
