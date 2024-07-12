package com.barbosa.frontend.thymeleafproductcatalog.model;

import lombok.Data;

@Data
public class Product {

    private String id;
    private String name;
    private Category category;
}
