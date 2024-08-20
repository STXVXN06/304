package com.steven.springboot.personalproject.personal_project.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fecha;

    private String direccion;

    private String nombreCliente;

    @ElementCollection
    private List<ProductoCantidad> productos;

    private String estado;

    private Double total;

    public Order() {
        this.productos = new ArrayList<>();
    }

    public Order(Date fecha, String direccion, String nombreCliente, List<ProductoCantidad> productos, String estado,
            Double total) {
        this();
        this.fecha = fecha;
        this.direccion = direccion;
        this.nombreCliente = nombreCliente;
        this.productos = productos;
        this.estado = estado;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public List<ProductoCantidad> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoCantidad> productos) {
        this.productos = productos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Embeddable
    public static class ProductoCantidad {
        private Long productoId;
        private int cantidad;

        public ProductoCantidad(Long productoId, int cantidad) {
            this.productoId = productoId;
            this.cantidad = cantidad;
        }

        public ProductoCantidad() {
        }

        public Long getProductoId() {
            return productoId;
        }

        public void setProductoId(Long productoId) {
            this.productoId = productoId;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

    }
}
