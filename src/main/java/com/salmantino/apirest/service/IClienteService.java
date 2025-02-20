package com.salmantino.apirest.service;

import com.salmantino.apirest.model.dto.ClienteDto;
import com.salmantino.apirest.model.entity.Cliente;

import java.util.List;

public interface IClienteService {

    List<Cliente> listAlll();

    Cliente save(ClienteDto cliente);

    Cliente findById(Integer id);

    void delete(Cliente cliente);

    boolean existsById(Integer id);

}

