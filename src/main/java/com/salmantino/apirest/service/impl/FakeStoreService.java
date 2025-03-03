package com.salmantino.apirest.service.impl;

import com.salmantino.apirest.model.dao.ProductoDao;
import com.salmantino.apirest.model.dto.FakeStoreProductoDto;
import com.salmantino.apirest.model.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreService {

    private static final String FAKE_STORE_URL = "https://fakestoreapi.com/products";

    @Autowired
    private ProductoDao productoDao;

    @Autowired
    private RestTemplate restTemplate;

    @PostConstruct // <- Se ejecuta automáticamente al arrancar la app
    public void poblarBaseDeDatos() {
        // Llamar a la API y obtener los productos
        FakeStoreProductoDto[] productosApi = restTemplate.getForObject(FAKE_STORE_URL, FakeStoreProductoDto[].class);

        if (productosApi != null) {
            List<FakeStoreProductoDto> listaProductos = Arrays.asList(productosApi);

            // Recorrer los productos de la API
            for (FakeStoreProductoDto apiProducto : listaProductos) {
                // Verificar si el producto ya existe en la base de datos (puedes usar 'nombre' o 'id' como criterio)
                if (!productoDao.existsByNombre(apiProducto.getTitle())) {
                    Producto producto = new Producto();
                    producto.setNombre(apiProducto.getTitle());
                    producto.setPrecio(apiProducto.getPrice());
                    producto.setDescripcion(apiProducto.getDescription());
                    producto.setImagenUrl(apiProducto.getImage());

                    // Guardar el producto en la base de datos
                    productoDao.save(producto);
                }
            }
        }
    }
}

