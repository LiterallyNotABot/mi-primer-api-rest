package com.salmantino.apirest.service.impl;

import com.salmantino.apirest.model.dao.CarritoDao;
import com.salmantino.apirest.model.dao.ItemCarritoDao;
import com.salmantino.apirest.model.dao.ProductoDao;
import com.salmantino.apirest.model.entity.Carrito;
import com.salmantino.apirest.model.entity.Cliente;
import com.salmantino.apirest.model.entity.ItemCarrito;
import com.salmantino.apirest.model.entity.Producto;
import com.salmantino.apirest.service.ICarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoServiceImpl implements ICarritoService {

    private final CarritoDao carritoDao;
    private final ItemCarritoDao itemCarritoDao;
    private final ProductoDao productoDao;

    @Autowired
    public CarritoServiceImpl(CarritoDao carritoDao, ItemCarritoDao itemCarritoDao, ProductoDao productoDao) {
        this.carritoDao = carritoDao;
        this.itemCarritoDao = itemCarritoDao;
        this.productoDao = productoDao;
    }

    @Override
    public Optional<Carrito> obtenerCarritoPorCliente(int clienteId) {
        // Buscamos el carrito por el id_cliente
        return carritoDao.findByIdCliente(clienteId);  // Retornamos Optional como está en la interfaz
    }

    @Override
    public void agregarItemAlCarrito(int clienteId, int productoId, int cantidad) {
        // Primero obtenemos el carrito del cliente
        Optional<Carrito> carritoOpt = obtenerCarritoPorCliente(clienteId);
        Carrito carrito = carritoOpt.orElseGet(() -> {
            // Si no existe el carrito, lo creamos
            Carrito newCarrito = new Carrito();
            newCarrito.setCliente(new Cliente(clienteId));  // Asociamos el cliente al carrito
            newCarrito.setTotal(0);
            return carritoDao.save(newCarrito);
        });

        // Buscamos el producto a agregar al carrito
        Producto producto = productoDao.findById(productoId).orElse(null);

        if (producto != null) {
            // Creamos el item del carrito
            ItemCarrito itemCarrito = new ItemCarrito();
            itemCarrito.setCarrito(carrito);
            itemCarrito.setProducto(producto);
            itemCarrito.setCantidad(cantidad);
            itemCarrito.setSubtotal(producto.getPrecio() * cantidad);
            itemCarritoDao.save(itemCarrito);

            // Actualizamos el total del carrito
            double total = calcularTotal(carrito.getId());
            carrito.setTotal(total);
            carritoDao.save(carrito);
        }
    }

    @Override
    public void eliminarItemDelCarrito(int clienteId, int itemId) {
        Optional<ItemCarrito> itemCarritoOpt = itemCarritoDao.findById(itemId);

        if (itemCarritoOpt.isPresent()) {
            ItemCarrito itemCarrito = itemCarritoOpt.get();
            if (itemCarrito.getCarrito().getCliente().getIdCliente() == clienteId) {
                itemCarritoDao.delete(itemCarrito);

                // Actualizamos el total después de eliminar el item
                double total = calcularTotal(itemCarrito.getCarrito().getId());
                Carrito carrito = itemCarrito.getCarrito();
                carrito.setTotal(total);
                carritoDao.save(carrito);
            }
        }
    }

    @Override
    public double calcularTotal(int carritoId) {
        List<ItemCarrito> items = itemCarritoDao.findByCarritoId(carritoId);
        double total = 0;
        for (ItemCarrito item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    @Override
    public List<ItemCarrito> obtenerItemsCarrito(int carritoId) {
        return itemCarritoDao.findByCarritoId(carritoId);
    }
}
