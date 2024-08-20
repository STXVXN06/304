package com.steven.springboot.personalproject.personal_project.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.steven.springboot.personalproject.personal_project.entities.Order;
import com.steven.springboot.personalproject.personal_project.services.OrderService;

@RestController
@RequestMapping("304/orders")
@CrossOrigin("http://localhost:3000")
public class OrderController {

    private final static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> list() {
        return orderService.findAll();
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        logger.info(order.toString());
        return orderService.save(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Order> orderOptional = orderService.findById(id);
        if (orderOptional.isPresent()) {
            return ResponseEntity.ok().body(orderOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

     @PutMapping("/{id}")
    public ResponseEntity<Order> update(@PathVariable Long id, @RequestBody Order order) {
        Optional<Order> orderOptional = orderService.update(id, order);
        if (orderOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(orderOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
