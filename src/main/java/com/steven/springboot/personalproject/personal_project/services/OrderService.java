package com.steven.springboot.personalproject.personal_project.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.steven.springboot.personalproject.personal_project.entities.Order;
import com.steven.springboot.personalproject.personal_project.entities.Product;
import com.steven.springboot.personalproject.personal_project.repositories.IngredientRepository;
import com.steven.springboot.personalproject.personal_project.repositories.OrderRepository;
import com.steven.springboot.personalproject.personal_project.repositories.ProductRepository;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Order> findAll() {
        return (List<Order>) orderRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    @Override
    public Order save(Order order) {
        order.setFecha(new Date());
        order.setEstado("Pendiente");

        // Calcular total
        double total = 0.0;
        for (Order.ProductoCantidad pc : order.getProductos()) {
            double precio = obtenerPrecioProducto(pc.getProductoId());
            total += precio * pc.getCantidad();
            order.setTotal(total);
        }

        return orderRepository.save(order);
    }


    @Transactional(readOnly = true)
    private double obtenerPrecioProducto(Long productoId) {
        // Lógica para obtener el precio del producto basado en su ID
        Optional<Product> productOptional = productRepository.findByIdWithIngredientes(productoId);
        if (productOptional.isPresent()) {
            return productOptional.orElseThrow().getPrecio();
        }

        return 0;
    }

    @Transactional
    @Override
    public Optional<Order> update(Long id, Order order) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order ord = orderOptional.orElseThrow();
            ord.setFecha(order.getFecha());
            ord.setDireccion(order.getDireccion());
            ord.setEstado(order.getEstado());
            ord.setNombreCliente(order.getNombreCliente());
            double total = 0.0;
            for (Order.ProductoCantidad pc : order.getProductos()) {
                double precio = obtenerPrecioProducto(pc.getProductoId());
                total += precio * pc.getCantidad();
            }
            ord.setTotal(total);
            ord.setProductos(order.getProductos());

            return Optional.of(orderRepository.save(ord));
        }

        return orderOptional;
    }

    @Transactional
    @Override
    public Optional<Order> delete(Long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        orderOptional.ifPresent(ord -> {
            orderRepository.delete(ord);
        });
        return orderOptional;
    }

}
