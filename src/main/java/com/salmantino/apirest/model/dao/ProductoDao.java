package com.salmantino.apirest.model.dao;

import com.salmantino.apirest.model.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoDao extends JpaRepository<Producto, Integer> {
    boolean existsByNombre(String nombre);
}
