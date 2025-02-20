package com.salmantino.apirest.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

// anotaciones lombok y 2 Notaciones JPA Java Persistence API
@Data  //Genera automáticamente getters, setters, equals(), hashCode(), y toString(). Simplifica la creación de clases POJO.
@AllArgsConstructor  //Crea un constructor con todos los campos de la clase como parámetros.
@NoArgsConstructor  //Crea un constructor sin parámetros necesario para JPA y frameworks que necesitan instanciar la clase sin argumentos.
@ToString //Genera un método toString() que incluye todos los campos de la clase.
@Builder  //patrón de diseño Builder crea instancias de la clase de manera fluida
@Entity //Indica que esta clase es una entidad JPA que se mapeará a una tabla en la base de datos.
@Table(name = "clientes")  //Especifica que la tabla asociada en la base de datos se llama clientes.
public class Cliente implements Serializable { //convertir en un flujo de bytes

    @Id
    @Column(name = "id_cliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //versión 8 de Mysql para Mysql 5.0 usa AUTO
    private Integer idCliente;

    @NotNull
    @Column(name = "nombre")
    private String nombre;

    @NotNull
    @Column(name = "apellido")
    private String apellido;

    @Email
    @Column(name = "correo", unique = true)
    private String correo;

    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

}