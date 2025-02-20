package com.salmantino.apirest.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoDto {
    private String title; // Nombre del producto
    private double price; // Precio del producto
    private String description; // Descripción del producto
    private String image; // URL de la imagen (lo agregas aquí)
}

