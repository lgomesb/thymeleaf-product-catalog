package com.barbosa.frontend.thymeleafproductcatalog.services;

import com.barbosa.frontend.thymeleafproductcatalog.config.CustomPageImpl;
import com.barbosa.frontend.thymeleafproductcatalog.model.Product;
import com.barbosa.frontend.thymeleafproductcatalog.model.dtos.ProductRequestDTO;
import com.barbosa.frontend.thymeleafproductcatalog.model.records.CategoryRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Service
public class ProductService {

    @Value("${endpoint.inventory.api.product}")
    private String productEndpoint;

    private final RestTemplate rest = new RestTemplate();


    public CategoryRecord getCategory(UUID id) {
        return null;
    }

    public void create(Product product) {
        
        try {
            ProductRequestDTO dto = new ProductRequestDTO(product.getName(), product.getCategory().getId());
            HttpEntity<ProductRequestDTO> request = new HttpEntity<>(dto);
            URI postForLocation = rest.postForLocation(productEndpoint, request);

            System.out.println(":".repeat(70) + " " + "RESPONSE");
            System.out.println(postForLocation.getPath());

        } catch (Exception e) {
            e.printStackTrace();
        }
        
		System.out.println(product);
    }

    public List<Product> listAll() {
        Page<Product> products;
        
        try {
            ResponseEntity<CustomPageImpl<Product>> response = rest.exchange(productEndpoint,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {
                    });

            products = response.getBody();
        } catch (Exception e) {
            products = new PageImpl<>(Collections.emptyList());
            e.printStackTrace();
        }
        assert products != null;
        return products.stream().toList();
    }

    public Page<Product> getListByPagination(int page) {
        Page<Product> products;
        int size = 10;
        int init = (page > 0) ? page - 1 : page;

        try {
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(productEndpoint)
                    .queryParam("page", init)
                    .queryParam("linesPerPage", size);

            ResponseEntity<CustomPageImpl<Product>> response = rest.exchange(uriBuilder.toUriString(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {
                    });

            products = response.getBody();
        } catch (Exception e) {
            products = new PageImpl<>(Collections.emptyList());
            e.printStackTrace();
        }
        assert products != null;
        return products;
    }


}
