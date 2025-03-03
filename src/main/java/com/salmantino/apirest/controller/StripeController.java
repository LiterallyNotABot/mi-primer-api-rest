package com.salmantino.apirest.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;

@RestController
public class StripeController {
    /*
    @Value("${stripe.api.key}")
    private String stripeApiKey;

    // Ruta para crear una sesión de pago
    @PostMapping("/create-checkout-session")
    public RedirectView createCheckoutSession() throws StripeException {

        // Configurar la clave de la API
        Stripe.apiKey = stripeApiKey;

        // Crear los parámetros de la sesión de pago
        SessionCreateParams params = SessionCreateParams.builder().setPaymentMethodOptions()
                .setPaymentMethodOptions(
                        Arrays.asList("card")) // Asegurándonos de que se acepte tarjeta de crédito
                .setLineItems(
                        Arrays.asList(
                                SessionCreateParams.LineItem.builder()
                                        .setPriceData(
                                                SessionCreateParams.LineItem.PriceData.builder()
                                                        .setCurrency("usd")
                                                        .setUnitAmount(500L) // 5.00 USD
                                                        .setProductData(
                                                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                        .setName("Producto de ejemplo")
                                                                        .build())
                                                        .build())
                                        .setQuantity(1L)
                                        .build()))
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:8092/success")
                .setCancelUrl("http://localhost:8092/cancel")
                .build();

        // Crear la sesión en Stripe
        Session session = Session.create(params);

        // Redirigir al cliente a la URL de la sesión de pago en Stripe
        return new RedirectView(session.getUrl());
    }

    // Ruta para la página de éxito
    @GetMapping("/success")
    public String success() {
        return "¡Pago realizado con éxito!";
    }

    // Ruta para la página de cancelación
    @GetMapping("/cancel")
    public String cancel() {
        return "El pago ha sido cancelado.";
    }
    */

}
