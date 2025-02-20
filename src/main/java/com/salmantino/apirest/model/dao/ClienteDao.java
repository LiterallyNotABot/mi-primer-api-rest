package com.salmantino.apirest.model.dao;

import com.salmantino.apirest.model.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

//JpaRepository quedó obsoleto deprecado
//PaginAndSortingRepository<Cliente,Integer>
public interface ClienteDao extends CrudRepository<Cliente,Integer> {

}
