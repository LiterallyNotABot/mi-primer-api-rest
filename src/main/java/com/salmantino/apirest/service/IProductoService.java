package com.salmantino.apirest.service;

import com.salmantino.apirest.model.entity.Producto;

import java.util.List;

public interface IProductoService {
    List<Producto> obtenerTodosLosProductos();  // Método para obtener los productos
    Producto guardarProducto(Producto producto);  // Método para guardar un producto
}