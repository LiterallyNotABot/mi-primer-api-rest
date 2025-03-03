package com.salmantino.apirest.controller;

import com.salmantino.apirest.model.entity.Producto;
import com.salmantino.apirest.service.impl.StripeService;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PagoController {

    @Autowired
    private StripeService stripeService;

    @PostMapping("/crear-sesion-pago")
    public String crearSesionDePago(@RequestBody List<Producto> productos) {
        try {
            Session session = stripeService.crearSesionDePago(productos);
            return session.getUrl();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al crear sesión de pago. Inténtelo más tarde.";
        }
    }
}
