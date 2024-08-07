package com.barbosa.frontend.thymeleafproductcatalog.controllers;

import com.barbosa.frontend.thymeleafproductcatalog.model.Category;
import com.barbosa.frontend.thymeleafproductcatalog.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping({ "/", "/category" })
public class CategoryController {

	private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

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

	@GetMapping("/list")
	public String list(ModelMap model) {
		model.addAttribute("categories", service.listAll()); 
		return "/category/list";
	}

}
