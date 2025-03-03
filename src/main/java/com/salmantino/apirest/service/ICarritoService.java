package com.salmantino.apirest.service;

import com.salmantino.apirest.model.entity.Carrito;
import com.salmantino.apirest.model.entity.ItemCarrito;

import java.util.List;
import java.util.Optional;

public interface ICarritoService {
    Optional<Carrito> obtenerCarritoPorCliente(int clienteId);  // Cambié el tipo de retorno a Optional
    void agregarItemAlCarrito(int clienteId, int productoId, int cantidad);
    void eliminarItemDelCarrito(int clienteId, int itemId);
    double calcularTotal(int carritoId);
    List<ItemCarrito> obtenerItemsCarrito(int carritoId);
}
