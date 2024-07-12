package com.barbosa.frontend.thymeleafproductcatalog.model.dtos;

import lombok.Data;

@Data
public class ProductRequestDTO {
    private final String name;
    private final String idCategory;
}
