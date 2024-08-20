package com.steven.springboot.personalproject.personal_project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIngrediente;
    private String nombreIngrediente;
    private String unidadMedida;
    private Integer cantidadActual;
    private Double cantidadMinima;
    private Double costo;

    public Ingredient() {

    }

    public Ingredient(String nombreIngrediente, String unidadMedida, Integer cantidadActual, Double cantidadMinima,
            Double costo) {
        this.nombreIngrediente = nombreIngrediente;
        this.unidadMedida = unidadMedida;
        this.cantidadActual = cantidadActual;
        this.cantidadMinima = cantidadMinima;
        this.costo = costo;
    }

    public Long getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(Long idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Integer getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(Integer cantidadActual) {
        this.cantidadActual = cantidadActual;
    }

    public Double getCantidadMinima() {
        return cantidadMinima;
    }

    public void setCantidadMinima(Double cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idIngrediente == null) ? 0 : idIngrediente.hashCode());
        result = prime * result + ((nombreIngrediente == null) ? 0 : nombreIngrediente.hashCode());
        result = prime * result + ((unidadMedida == null) ? 0 : unidadMedida.hashCode());
        result = prime * result + ((cantidadActual == null) ? 0 : cantidadActual.hashCode());
        result = prime * result + ((cantidadMinima == null) ? 0 : cantidadMinima.hashCode());
        result = prime * result + ((costo == null) ? 0 : costo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ingredient other = (Ingredient) obj;
        if (idIngrediente == null) {
            if (other.idIngrediente != null)
                return false;
        } else if (!idIngrediente.equals(other.idIngrediente))
            return false;
        if (nombreIngrediente == null) {
            if (other.nombreIngrediente != null)
                return false;
        } else if (!nombreIngrediente.equals(other.nombreIngrediente))
            return false;
        if (unidadMedida == null) {
            if (other.unidadMedida != null)
                return false;
        } else if (!unidadMedida.equals(other.unidadMedida))
            return false;
        if (cantidadActual == null) {
            if (other.cantidadActual != null)
                return false;
        } else if (!cantidadActual.equals(other.cantidadActual))
            return false;
        if (cantidadMinima == null) {
            if (other.cantidadMinima != null)
                return false;
        } else if (!cantidadMinima.equals(other.cantidadMinima))
            return false;
        if (costo == null) {
            if (other.costo != null)
                return false;
        } else if (!costo.equals(other.costo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "{idIngrediente=" + idIngrediente + ", nombreIngrediente=" + nombreIngrediente
                + ", unidadMedida=" + unidadMedida + ", cantidadActual=" + cantidadActual + ", cantidadMinima="
                + cantidadMinima + ", costo=" + costo + "}";
    }

}
