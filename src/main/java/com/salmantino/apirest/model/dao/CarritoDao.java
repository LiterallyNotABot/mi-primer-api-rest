package com.salmantino.apirest.model.dao;

import com.salmantino.apirest.model.entity.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarritoDao extends JpaRepository<Carrito, Integer> {
    // Método para encontrar el carrito por id_cliente
    Optional<Carrito> findByIdCliente(int idCliente);
}
