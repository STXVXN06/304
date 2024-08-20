package com.steven.springboot.personalproject.personal_project.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.steven.springboot.personalproject.personal_project.entities.Ingredient;
import com.steven.springboot.personalproject.personal_project.entities.Product;
import com.steven.springboot.personalproject.personal_project.repositories.IngredientRepository;
import com.steven.springboot.personalproject.personal_project.repositories.ProductRepository;

@Service
public class ProductService implements IProductService{


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return productRepository.findAllWithIngredientesAndReceta();
    }
    
    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findByIdWithIngredientes(id);
    }


    @Transactional
    @Override
    public Product save(Product product) {
        Set<Long> idsIngredientes = product.getIngredientes().stream()
                .map(ingrediente -> ingrediente.getIdIngrediente())
                .collect(Collectors.toSet());

        // Obtener los objetos completos de Ingrediente correspondientes a los IDs
        Set<Ingredient> ingredientes = idsIngredientes.stream()
                .map(idIngrediente -> ingredientRepository.findById(idIngrediente).orElseThrow())
                .collect(Collectors.toSet());

        // Asignar los ingredientes al producto
        product.setIngredientes(ingredientes);
        return productRepository.save(product);
    }


    @Transactional
    @Override
    public Optional<Product> update(Long id, Product product){
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product prod = productOptional.orElseThrow();
            prod.setNombreProducto(product.getNombreProducto());
            prod.setDescripcion(product.getDescripcion());
            prod.setPrecio(product.getPrecio());
            prod.setStock(product.getStock());
            return Optional.of(productRepository.save(prod));
        }

        return productOptional;
    }
    
    @Transactional
    @Override
    public Optional<Product> delete(Long id ) {
        Optional<Product> productOptional = productRepository.findByIdWithIngredientes(id);
        productOptional.ifPresent(prod -> {
            productRepository.delete(prod);
        });
        return productOptional;
    }

    @Override
    public Optional<Product> findByIdWithIngredients(Long id) {
        return productRepository.findByIdWithIngredientes(id);
    }
    

}
