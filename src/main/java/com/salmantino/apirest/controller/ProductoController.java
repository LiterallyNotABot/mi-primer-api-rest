package com.salmantino.apirest.controller;

import com.salmantino.apirest.model.dao.ProductoDao;
import com.salmantino.apirest.model.entity.Producto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class ProductoController {

    private final ProductoDao productoDao;

    // Inyección de dependencias para acceder a la base de datos
    public ProductoController(ProductoDao productoDao) {
        this.productoDao = productoDao;
    }

    // Este método maneja la solicitud GET a /productos y pasa los productos al modelo
    @GetMapping("/productos")
    public String showProductosPage(Model model) {
        // Obtener todos los productos de la base de datos
        List<Producto> productos = productoDao.findAll();

        // Añadir los productos al modelo
        model.addAttribute("productos", productos);

        // Devuelve la plantilla productos.html
        return "productos";
    }
}
