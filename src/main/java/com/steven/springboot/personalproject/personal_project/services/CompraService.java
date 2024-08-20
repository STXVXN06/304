package com.steven.springboot.personalproject.personal_project.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.steven.springboot.personalproject.personal_project.entities.Compra;
import com.steven.springboot.personalproject.personal_project.entities.Ingredient;
import com.steven.springboot.personalproject.personal_project.repositories.CompraRepository;
import com.steven.springboot.personalproject.personal_project.repositories.IngredientRepository;

@Service
public class CompraService implements ICompraService{
@Autowired
    private CompraRepository compraRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Compra> findAll() {
        return (List<Compra>) compraRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Compra> findById(Long id) {
        return compraRepository.findByIdWithIngredientes(id);
    }

    @Transactional
    @Override
    public Compra save(Compra compra) {
        if ("ingrediente".equalsIgnoreCase(compra.getTipoCompra()) && compra.getIngrediente() != null) {
            // Actualizar la cantidad de ingrediente si es una compra de tipo "ingrediente"
            Ingredient ingrediente = ingredientRepository.findById(compra.getIngrediente().getIdIngrediente())
                    .orElseThrow(() -> new RuntimeException("Ingrediente no encontrado"));

            ingrediente.setCantidadActual(ingrediente.getCantidadActual() + compra.getCantidad());
            ingredientRepository.save(ingrediente);
        }
        compra.setFechaCompra(new Date());
        
        return compraRepository.save(compra);
    }

    @Transactional
    @Override
    public Optional<Compra> update(Long id, Compra compra) {
        Optional<Compra> compraOptional = compraRepository.findById(id);
        if (compraOptional.isPresent()) {
            Compra compraExistente = compraOptional.orElseThrow();
            compraExistente.setTipoCompra(compra.getTipoCompra());
            compraExistente.setCantidad(compra.getCantidad());
            compraExistente.setMarca(compra.getMarca());
            compraExistente.setProveedor(compra.getProveedor());
            compraExistente.setFechaCompra(compra.getFechaCompra());
            compraExistente.setCostoTotal(compra.getCostoTotal());

            // Si se trata de un ingrediente, actualizamos el ingrediente
            if ("ingrediente".equalsIgnoreCase(compra.getTipoCompra()) && compra.getIngrediente() != null) {
                compraExistente.setIngrediente(compra.getIngrediente());
            } else {
                // Si no es un ingrediente, solo actualizamos el otro artículo
                compraExistente.setOtroArticulo(compra.getOtroArticulo());
            }

            return Optional.of(compraRepository.save(compraExistente));
        }
        return compraOptional;
    }

    @Transactional
    @Override
    public Optional<Compra> delete(Long id) {
        Optional<Compra> compraOptional = compraRepository.findById(id);
        compraOptional.ifPresent(compra -> compraRepository.delete(compra));
        return compraOptional;
    }

    @Override
    public Optional<Compra> findByIdWithIngredients(Long id) {
        return compraRepository.findByIdWithIngredientes(id);
    }

}
