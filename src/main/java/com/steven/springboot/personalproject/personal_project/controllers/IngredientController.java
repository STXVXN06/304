package com.steven.springboot.personalproject.personal_project.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.steven.springboot.personalproject.personal_project.entities.Ingredient;
import com.steven.springboot.personalproject.personal_project.entities.Product;
import com.steven.springboot.personalproject.personal_project.services.IngredientService;

@RestController
@RequestMapping("304/ingredients")
@CrossOrigin(value = "http://localhost:3000")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    public List<Ingredient> obtenerIngredientes() {
        return ingredientService.findAll();
    }

    @PostMapping
    public Ingredient agregarIngrediente(@RequestBody Ingredient ingredient) {
        return ingredientService.save(ingredient);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> obtenerIngredientePorId(
            @PathVariable Long id) {
        Optional<Ingredient> ingredienteOptional = ingredientService.findById(id);

        if (ingredienteOptional.isPresent()) {
            return ResponseEntity.ok().body(ingredienteOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> update(@PathVariable Long id, @RequestBody Ingredient ingredient) {
        Optional<Ingredient> ingredientOptional = ingredientService.update(id, ingredient);
        if (ingredientOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(ingredientOptional.orElseThrow());

        }
        return ResponseEntity.notFound().build();

    }

       @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Ingredient> ingredientOptional = ingredientService.delete(id);
        if (ingredientOptional.isPresent()) {
            return ResponseEntity.ok().body(ingredientOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
