package com.salmantino.apirest.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "nombre", unique = true, length = 100) // Limitar el nombre a 100 caracteres, si es necesario
    private String nombre;

    @Column(name = "precio", nullable = false)
    private double precio;

    @Lob
    @Column(name = "descripcion", columnDefinition = "LONGTEXT")
    private String descripcion;

    // Nuevo campo para la URL de la imagen
    @Column(name = "imagen_url")
    private String imagenUrl; // Aquí guardamos la URL de la imagen
}
