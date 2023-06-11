package com.barbosa.frontend.thymeleafproductcatalog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.barbosa.frontend.thymeleafproductcatalog.model.Form;


@Controller
@RequestMapping({ "/", "/index" })
public class HomeController {
    
    @GetMapping
    public String index(Model model) {
        model.addAttribute("form", new Form());
        model.addAttribute("name", "John"); // set 'John' value for 'name' attribute

        return "index"; // name of the View
    }

    @PostMapping
    public String save(Form form, Model model) {
        model.addAttribute("form", form);
        System.out.println("-".repeat(30) + "> Result: ");
        System.out.println(form.toString());
        return "index";
    }

}
