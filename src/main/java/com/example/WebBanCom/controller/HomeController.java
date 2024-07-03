package com.example.WebBanCom.controller;

import com.example.WebBanCom.model.Product;
import com.example.WebBanCom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ProductRepository productRepository; // Inject repository hoặc service của Product

    @GetMapping("/home")
    public String hello(Model model) {
        List<Product> products = productRepository.findAll(); // Truy vấn dữ liệu Product từ repository hoặc service
        model.addAttribute("products", products); // Truyền dữ liệu vào model

        model.addAttribute("message", "KIỂM TRA GIỮA KÌ, LÝ THUYẾT JAVA!");
        return "home/index";
    }
}