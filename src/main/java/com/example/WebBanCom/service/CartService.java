package com.example.WebBanCom.service;

import com.example.WebBanCom.model.CartItem;
import com.example.WebBanCom.model.Product;
import com.example.WebBanCom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
public class CartService {
    private List<CartItem> cartItems = new ArrayList<>();
    @Autowired
    private ProductRepository productRepository;
    public void addToCart(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found:" + productId));

        // Check if the product is already in the cart
        for (CartItem item : cartItems) {
            if (item.getProduct().getId().equals(productId)) {
                // Increase the quantity of the existing item
                item.setQuantity(item.getQuantity() + quantity);
                return; // Exit the method
            }
        }

        // If the product is not in the cart, add it as a new item
        cartItems.add(new CartItem(product, quantity));
    }
    public List<CartItem> getCartItems() {
        return cartItems;
    }
    public void removeFromCart(Long productId) {
        cartItems.removeIf(item -> item.getProduct().getId().equals(productId));
    }
    public void clearCart() {
        cartItems.clear();
    }

    public void increaseQuantity(Long productId, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId().equals(productId)) {
                int newQuantity = item.getQuantity() + quantity;
                item.setQuantity(newQuantity);
                return;
            }
        }
    }

    public void decreaseQuantity(Long productId, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId().equals(productId)) {
                int newQuantity = item.getQuantity() - quantity;
                if (newQuantity >= 1) {
                    item.setQuantity(newQuantity);
                }
                return;
            }
        }
    }

}
