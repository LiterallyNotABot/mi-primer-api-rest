package com.salmantino.apirest.controller;

import com.salmantino.apirest.service.impl.FakeStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    private FakeStoreService fakeStoreService;

    @PostMapping("/admin/poblar-productos")
    public String poblarProductos() {
        fakeStoreService.poblarBaseDeDatos();
        return "Base de datos poblada con productos de FakeStoreAPI";
    }
}
