package com.example.WebBanCom.controller;

import com.example.WebBanCom.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @GetMapping
    public String showCart(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        return "/cart/cart";
    }
    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId, @RequestParam int
            quantity) {
        cartService.addToCart(productId, quantity);
        return "redirect:/cart";
    }
    @GetMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable Long productId) {
        cartService.removeFromCart(productId);
        return "redirect:/cart";
    }
    @GetMapping("/clear")
    public String clearCart() {
        cartService.clearCart();
        return "redirect:/cart";
    }
    @PostMapping("/increase/{productId}/{quantity}")
    public String increaseQuantity(@PathVariable Long productId, @PathVariable int quantity) {
        cartService.increaseQuantity(productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/decrease/{productId}/{quantity}")
    public String decreaseQuantity(@PathVariable Long productId, @PathVariable int quantity) {
        cartService.decreaseQuantity(productId, quantity);
        return "redirect:/cart";
    }
}

