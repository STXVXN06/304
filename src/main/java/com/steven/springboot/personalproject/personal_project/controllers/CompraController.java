package com.steven.springboot.personalproject.personal_project.controllers;

import java.util.List;
import java.util.Optional;

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

import com.steven.springboot.personalproject.personal_project.entities.Compra;
import com.steven.springboot.personalproject.personal_project.services.CompraService;

@RestController
@RequestMapping("304/compras")
@CrossOrigin("http://localhost:3000")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @GetMapping
    public List<Compra> list() {
        return compraService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Compra> compraOptional = compraService.findById(id);
        if (compraOptional.isPresent()) {
            return ResponseEntity.ok().body(compraOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Compra> create(@RequestBody Compra compra) {
        Compra compraNew = compraService.save(compra);
        return ResponseEntity.status(HttpStatus.CREATED).body(compraNew);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Compra> update(@PathVariable Long id, @RequestBody Compra compra) {
        Optional<Compra> compraOptional = compraService.update(id, compra);
        if (compraOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(compraOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

     @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Compra> compraOptional = compraService.delete(id);
        if (compraOptional.isPresent()) {
            return ResponseEntity.ok().body(compraOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}
