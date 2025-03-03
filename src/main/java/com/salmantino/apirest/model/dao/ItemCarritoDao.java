package com.salmantino.apirest.model.dao;

import com.salmantino.apirest.model.entity.ItemCarrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCarritoDao extends JpaRepository<ItemCarrito, Integer> {
}
