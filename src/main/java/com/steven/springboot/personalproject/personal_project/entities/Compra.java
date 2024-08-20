package com.steven.springboot.personalproject.personal_project.entities;



import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "compras")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCompra;

    private String tipoCompra; // "ingrediente" o "otro"
    private String otroArticulo; // En caso de que no sea un ingrediente
    private Integer cantidad;
    private String marca;
    private String proveedor;
    private Date fechaCompra;
    private Double costoTotal;
    private Double costoPorUnidad; // Nuevo campo para almacenar el costo por unidad

    @ManyToOne
    @JoinColumn(name = "idIngrediente")
    private Ingredient ingrediente;

    public Compra() {
    }

    public Compra(String tipoCompra, String otroArticulo, Integer cantidad, String marca, String proveedor,
            Date fechaCompra, Double costoTotal, Ingredient ingrediente) {
        this.tipoCompra = tipoCompra;
        this.otroArticulo = otroArticulo;
        this.cantidad = cantidad;
        this.marca = marca;
        this.proveedor = proveedor;
        this.fechaCompra = fechaCompra;
        this.costoTotal = costoTotal;
        this.costoPorUnidad = calcularCostoPorUnidad(); // Calcular el costo por unidad basado en el costo total y la
                                                        // cantidad
        this.ingrediente = ingrediente;
    }

    // Método para calcular el costo por unidad
    public Double calcularCostoPorUnidad() {
        if (cantidad != null && cantidad > 0 && costoTotal != null) {
            return costoTotal / cantidad;
        }
        return 0.0;
    }

    // Getters y Setters

    public Long getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Long idCompra) {
        this.idCompra = idCompra;
    }

    public String getTipoCompra() {
        return tipoCompra;
    }

    public void setTipoCompra(String tipoCompra) {
        this.tipoCompra = tipoCompra;
    }

    public String getOtroArticulo() {
        return otroArticulo;
    }

    public void setOtroArticulo(String otroArticulo) {
        this.otroArticulo = otroArticulo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
        this.costoPorUnidad = calcularCostoPorUnidad(); // Recalcular el costo por unidad cuando se actualice la
                                                        // cantidad
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
        this.costoPorUnidad = calcularCostoPorUnidad(); // Recalcular el costo por unidad cuando se actualice el costo
                                                        // total
    }

    public Double getCostoPorUnidad() {
        return costoPorUnidad;
    }

    public Ingredient getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingredient ingrediente) {
        this.ingrediente = ingrediente;
    }

    @Override
    public String toString() {
        return "{idCompra=" + idCompra + ", tipoCompra=" + tipoCompra + ", otroArticulo=" + otroArticulo +
                ", cantidad=" + cantidad + ", marca=" + marca + ", proveedor=" + proveedor +
                ", fechaCompra=" + fechaCompra + ", costoTotal=" + costoTotal +
                ", costoPorUnidad=" + costoPorUnidad +
                ", ingrediente=" + (ingrediente != null ? ingrediente.getNombreIngrediente() : "N/A") + "}";
    }
}
