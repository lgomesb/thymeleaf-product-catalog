package com.barbosa.frontend.thymeleafproductcatalog.services;

import java.net.URI;
import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.barbosa.frontend.thymeleafproductcatalog.model.Category;
import com.barbosa.frontend.thymeleafproductcatalog.model.records.CategoryRecord;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class CategoryService {

    @Value("${endpoint.inventory.api.category}")
    private String categoryEndpoint;

    private final RestTemplate rest = new RestTemplate();


    public Category getCategory(UUID id) {
        Category category = null;
        try {
            String url = UriComponentsBuilder.fromHttpUrl(categoryEndpoint)
                    .path("/")
                    .path(id.toString())
                    .toUriString();
            ResponseEntity<Category> response = rest.getForEntity(url, Category.class);
            category = response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }

    public void create(Category category) {
        
        try {
            HttpEntity<Category> request = new HttpEntity<>(category);
            // ResponseEntity<String> response = rest.exchange(CATEGORY_MS_URI, HttpMethod.POST, request, String.class);
            URI postForLocation = rest.postForLocation(categoryEndpoint, request);

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
            ResponseEntity<Category[]> response = rest.getForEntity(categoryEndpoint, Category[].class);
            categories = Arrays.asList(Objects.requireNonNull(response.getBody()));
        } catch (Exception e) {
            categories = new ArrayList<>();
            e.printStackTrace();
        } 
        return categories;
    }


}
