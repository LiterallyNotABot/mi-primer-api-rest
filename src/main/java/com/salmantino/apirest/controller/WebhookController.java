package com.salmantino.apirest.controller;

import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class WebhookController {

    @Value("${stripe.webhook.secret}") // Obtén el secreto desde el archivo de configuración
    private String webhookSecret;

    @PostMapping("/webhook")
    public String handleWebhook(HttpServletRequest request, @RequestBody String payload) {
        String sigHeader = request.getHeader("Stripe-Signature");
        Event event = null;

        try {
            // Verificar la firma del webhook
            event = Webhook.constructEvent(payload, sigHeader, webhookSecret);

            // Manejar el evento
            if ("checkout.session.completed".equals(event.getType())) {
                Session session = (Session) event.getData().getObject();

                // Aquí puedes procesar la información de la sesión
                // Por ejemplo, marcar el pedido como completado en la base de datos
                // Puedes acceder a los detalles de la sesión (productos, cliente, etc.)
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Webhook error: " + e.getMessage();
        }

        return "Webhook recibido con éxito";
    }
}
