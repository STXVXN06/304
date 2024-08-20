package com.steven.springboot.personalproject.personal_project.repositories;

import org.springframework.data.repository.CrudRepository;

import com.steven.springboot.personalproject.personal_project.entities.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{

}
