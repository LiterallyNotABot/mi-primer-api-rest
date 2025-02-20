package com.salmantino.apirest.controller;

import com.salmantino.apirest.model.entity.Producto;
import com.salmantino.apirest.model.dao.ProductoDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ProductoController {

    private final ProductoDao productoDao;

    public ProductoController(ProductoDao productoDao) {
        this.productoDao = productoDao;
    }

    @GetMapping("/productos")
    public List<Producto> obtenerProductos() {
        return productoDao.findAll();
    }
}
