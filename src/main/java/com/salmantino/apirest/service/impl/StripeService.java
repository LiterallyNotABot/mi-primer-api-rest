package com.salmantino.apirest.service.impl;

import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.param.checkout.SessionCreateParams.LineItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.salmantino.apirest.model.dao.ProductoDao;
import com.salmantino.apirest.model.entity.Producto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StripeService {

    @Autowired
    private ProductoDao productoDao;

    public Session crearSesionDePago(List<Producto> productos) throws Exception {
        // Crear los LineItems para Stripe (productos de la base de datos)
        List<LineItem> lineItems = productos.stream().map(producto -> LineItem.builder()
                .setPriceData(
                        LineItem.PriceData.builder()
                                .setCurrency("usd") // Moneda
                                .setUnitAmount((long) (producto.getPrecio() * 100)) // Precio en centavos
                                .setProductData(
                                        LineItem.PriceData.ProductData.builder()
                                                .setName(producto.getNombre()) // Nombre del producto
                                                .build()
                                )
                                .build())
                .setQuantity(1L) // Cantidad del producto
                .build()).collect(Collectors.toList());

        // Configurar los parámetros para la sesión
        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .addAllLineItem(lineItems) // Añadir todos los LineItems
                .setMode(SessionCreateParams.Mode.PAYMENT) // Modo de pago
                .setSuccessUrl("http://localhost:8092/success") // URL de éxito
                .setCancelUrl("http://localhost:8092/cancel") // URL de cancelación
                .build();

        // Crear la sesión en Stripe
        return Session.create(params);
    }
}
