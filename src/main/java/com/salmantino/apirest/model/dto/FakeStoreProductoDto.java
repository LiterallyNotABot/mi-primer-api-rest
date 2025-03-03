package com.salmantino.apirest.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductoDto {
    private Integer id;
    private String title;
    private Double price;
    private String description;
    private String image;
}
