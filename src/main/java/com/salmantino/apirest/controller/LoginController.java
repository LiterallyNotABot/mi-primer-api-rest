package com.salmantino.apirest.controller;

import com.salmantino.apirest.model.dao.ClienteDao;
import com.salmantino.apirest.model.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final ClienteDao clienteDao;

    @Autowired
    public LoginController(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    @PostMapping("/login")
    public String login(@RequestParam("correo") String correo, HttpSession session, Model model) {
        // Buscar cliente por correo
        Cliente cliente = clienteDao.findByCorreo(correo);

        if (cliente != null) {
            // Almacenar el id_cliente en la sesión
            session.setAttribute("clienteId", cliente.getIdCliente());

            // Redirigir a la página principal o al carrito
            return "redirect:/home"; // O donde desees redirigir después del login
        } else {
            // Si no se encuentra el cliente, mostrar un mensaje de error
            model.addAttribute("error", "Correo no encontrado.");
            return "login";  // Volver a la página de login
        }
    }
}
