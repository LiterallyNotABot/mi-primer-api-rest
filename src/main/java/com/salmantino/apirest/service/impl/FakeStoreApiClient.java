package com.salmantino.apirest.service.impl;

import com.salmantino.apirest.model.dto.ProductoDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Service
public class FakeStoreApiClient {

    private final WebClient webClient;

    public FakeStoreApiClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://fakestoreapi.com").build();
    }

    public List<ProductoDto> obtenerProductosExternos() {
        return webClient.get()
                .uri("/products")
                .retrieve()
                .bodyToFlux(ProductoDto.class)
                .collectList()
                .block(); // Bloquea hasta recibir los datos
    }
}
