package com.barbosa.frontend.thymeleafproductcatalog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.barbosa.frontend.thymeleafproductcatalog.model.Category;
import com.barbosa.frontend.thymeleafproductcatalog.services.CategoryService;


@Controller
@RequestMapping({ "/", "/category" })
public class CategoryController {

	@Autowired
	private CategoryService service;
    
	@GetMapping("/insert")
	public String create(Category category) {
		return "/category/insert";
	}

	@PostMapping("/salve")
	public String salve(Category category, RedirectAttributes attr) {		
		service.create(category);
		attr.addFlashAttribute("success", "Category created with successfully.");
		return "redirect:/category/insert";
	}

}
