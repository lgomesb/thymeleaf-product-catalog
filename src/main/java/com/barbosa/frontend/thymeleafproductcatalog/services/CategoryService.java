package com.barbosa.frontend.thymeleafproductcatalog.services;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.barbosa.frontend.thymeleafproductcatalog.model.Category;
import com.barbosa.frontend.thymeleafproductcatalog.model.records.CategoryRecord;

@Service
public class CategoryService {
    
    private static final String CATEGORY_MS_URI = "http://localhost:8080/category";

    private final RestTemplate rest = new RestTemplate();


    public CategoryRecord getCategory(UUID id) {
        return null;
    }

    public void create(Category category) {
        
        try {
            HttpEntity<Category> request = new HttpEntity<>(category);
            // ResponseEntity<String> response = rest.exchange(CATEGORY_MS_URI, HttpMethod.POST, request, String.class);
            URI postForLocation = rest.postForLocation(CATEGORY_MS_URI, request);

            System.out.println(":".repeat(70) + " " + "RESPONSE");

            System.out.println(postForLocation.getPath());

        } catch (Exception e) {
            e.printStackTrace();
        }
        
		System.out.println(category);
    }

    public List<Category> listAll() {
        List<Category> categories;
        
        try {
            ResponseEntity<Category[]> response = rest.getForEntity(CATEGORY_MS_URI, Category[].class);
            categories = Arrays.asList(response.getBody());
        } catch (Exception e) {
            categories = new ArrayList<>();
            e.printStackTrace();
        } 
        return categories;
    }


}
