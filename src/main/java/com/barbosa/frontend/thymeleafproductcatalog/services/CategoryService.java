package com.barbosa.frontend.thymeleafproductcatalog.services;

import java.net.http.HttpClient;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.barbosa.frontend.thymeleafproductcatalog.model.Category;
import com.barbosa.frontend.thymeleafproductcatalog.model.records.CategoryRecord;

@Service
public class CategoryService {
    
    public CategoryRecord getCategory(UUID id) {
        return null;
    }

    public void create(Category category) {
        
        System.out.println(":".repeat(70));
		System.out.println(category);
    }


}
