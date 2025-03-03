package com.salmantino.apirest.model.dao;

import com.salmantino.apirest.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteDao extends JpaRepository<Cliente, Integer> {

    Cliente findByCorreo(String correo);
}
