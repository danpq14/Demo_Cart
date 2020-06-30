package controllers;

import model.Product;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import service.ProductService;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/showListProduct")
    public String showList(Model model,
                           @PageableDefault(size = 5) Pageable pageable,
                           @SessionAttribute("user") User user) {
        Page<Product> products = productService.findAll(pageable);
        model.addAttribute("user", user);
        model.addAttribute("products", products);
        return "/product/list";
    }

}
