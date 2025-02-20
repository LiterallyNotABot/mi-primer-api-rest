package com.salmantino.apirest.service.impl;

import com.salmantino.apirest.model.dto.ProductoDto;
import com.salmantino.apirest.model.entity.Producto;
import com.salmantino.apirest.model.dao.ProductoDao;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.util.List;

@Service
public class ProductoSeederService {

    private final FakeStoreApiClient fakeStoreApiClient;
    private final ProductoDao productoDao;

    public ProductoSeederService(FakeStoreApiClient fakeStoreApiClient, ProductoDao productoDao) {
        this.fakeStoreApiClient = fakeStoreApiClient;
        this.productoDao = productoDao;
    }

    @PostConstruct
    public void poblarProductos() {
        // Obtener los productos desde la API externa
        List<ProductoDto> productosExternos = fakeStoreApiClient.obtenerProductosExternos();

        // Iterar sobre los productos y guardarlos en la base de datos
        for (ProductoDto dto : productosExternos) {
            if (!productoDao.existsByNombre(dto.getTitle())) { // Evitar duplicados por nombre
                Producto producto = new Producto();
                producto.setNombre(dto.getTitle());
                producto.setPrecio(dto.getPrice());
                producto.setDescripcion(dto.getDescription());
                producto.setImagenUrl(dto.getImage()); // Asigna la URL de la imagen
                productoDao.save(producto); // Guarda el producto en la base de datos
            }
        }
    }
}
