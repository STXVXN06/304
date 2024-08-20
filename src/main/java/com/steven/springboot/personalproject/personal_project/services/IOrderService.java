package com.steven.springboot.personalproject.personal_project.services;

import java.util.List;
import java.util.Optional;

import com.steven.springboot.personalproject.personal_project.entities.Order;


public interface IOrderService {

     List<Order> findAll();

    Optional<Order> findById(Long id);

    Order save(Order order);

    Optional<Order> update(Long id, Order order);

    Optional<Order> delete(Long id);
}
