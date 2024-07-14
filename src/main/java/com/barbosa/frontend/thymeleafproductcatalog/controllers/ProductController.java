package com.barbosa.frontend.thymeleafproductcatalog.controllers;

import com.barbosa.frontend.thymeleafproductcatalog.model.Category;
import com.barbosa.frontend.thymeleafproductcatalog.model.Product;
import com.barbosa.frontend.thymeleafproductcatalog.services.CategoryService;
import com.barbosa.frontend.thymeleafproductcatalog.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@RequestMapping({ "/", "/product" })
public class ProductController {

	private final ProductService productService;
	private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/insert")
	public String create(Product product) {
		return "/product/insert";
	}

	@PostMapping("/salve")
	public String salve(Product product, RedirectAttributes attr) {		
		productService.create(product);
		attr.addFlashAttribute("success", "Product created with successfully.");
		return "redirect:/product/insert";
	}

	@GetMapping("/list")
	public String list(ModelMap model,
					   @RequestParam("page") Optional<Integer> page,
					   @RequestParam("dir") Optional<String> dir) {
		String direction = dir.orElse("asc");
		Page<Product> productPage = productService.getListByPagination(page.orElse(1), direction);
		model.addAttribute("products", productPage.getContent());
		model.addAttribute("prodPage", productPage.getNumber()+1);
		int totalPages = productPage.getTotalPages();
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("dir", direction);

		if(totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
			model.addAttribute("pageNumbers", pageNumbers);
		}

		return "/product/list";
	}

	@ModelAttribute("categories")
	public List<Category> listAllCategories() {
		return categoryService.listAll();
	}

}
