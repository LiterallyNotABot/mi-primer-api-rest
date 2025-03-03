package com.salmantino.apirest.controller;

import com.salmantino.apirest.model.entity.Carrito;
import com.salmantino.apirest.model.entity.Producto;
import com.salmantino.apirest.service.impl.CarritoService;
import com.salmantino.apirest.model.dao.ProductoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class CarritoController {

    private final ProductoDao productoDao;
    private final CarritoService carritoService;

    @Autowired
    public CarritoController(ProductoDao productoDao, CarritoService carritoService) {
        this.productoDao = productoDao;
        this.carritoService = carritoService;
    }

    // Este método maneja el POST a /carrito/agregar y añade un producto al carrito
    @PostMapping("/carrito/agregar")
    public String agregarAlCarrito(@RequestParam("idProducto") int idProducto,
                                   @RequestParam("cantidad") int cantidad,
                                   HttpSession session, Model model) {
        // Obtener el ID del cliente desde la sesión
        Integer clienteId = (Integer) session.getAttribute("clienteId");

        if (clienteId == null) {
            // Si no hay cliente logueado, redirigir al login
            return "redirect:/login";
        }

        // Buscar el producto por su ID
        Producto producto = productoDao.findById(idProducto).orElse(null);

        if (producto != null) {
            // Obtener el carrito del cliente (si no tiene uno, se crea uno nuevo)
            Carrito carrito = carritoService.obtenerCarrito(clienteId);

            // Agregar el producto al carrito
            carritoService.agregarProductoAlCarrito(carrito, producto, cantidad);

            // Pasar el carrito actualizado al modelo para mostrarlo en la vista
            model.addAttribute("carrito", carrito);
            return "carrito";  // Redirige a la vista carrito.html donde mostramos los productos en el carrito
        } else {
            // Si no se encuentra el producto, agregar un mensaje de error.
            model.addAttribute("error", "Producto no encontrado.");
            return "carrito";  // Muestra un error en la misma vista de carrito
        }
    }
}
