package com.barbosa.frontend.thymeleafproductcatalog.conversor;

import com.barbosa.frontend.thymeleafproductcatalog.model.Category;
import com.barbosa.frontend.thymeleafproductcatalog.services.CategoryService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StringToCategoryConverter implements Converter<String, Category> {

    private final CategoryService service;

    public StringToCategoryConverter(CategoryService service) {
        this.service = service;
    }

    @Override
    public Category convert(String text) {
        if(text.isEmpty())
            return null;

        return service.getCategory(UUID.fromString(text));
    }
}
