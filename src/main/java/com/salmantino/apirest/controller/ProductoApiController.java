package com.salmantino.apirest.controller;

import com.salmantino.apirest.model.entity.Producto;
import com.salmantino.apirest.model.dao.ProductoDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ProductoApiController {

    private final ProductoDao productoDao;

    public ProductoApiController(ProductoDao productoDao) {
        this.productoDao = productoDao;
    }

    // Este método maneja la solicitud GET a /api/productos y devuelve los productos como JSON
    @GetMapping("/api/productos")
    public List<Producto> obtenerProductos() {
        return productoDao.findAll();
    }
}
