package com.salmantino.apirest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Este método maneja la solicitud GET a la raíz ("/") y retorna el nombre de la vista "index"
    @GetMapping("/")
    public String showIndexPage() {
        return "index";  // El nombre de la plantilla sin la extensión .html
    }
}
