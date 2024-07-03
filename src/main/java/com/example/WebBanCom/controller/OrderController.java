package com.example.WebBanCom.controller;

import com.example.WebBanCom.model.CartItem;
import com.example.WebBanCom.model.Order;
import com.example.WebBanCom.service.CartService;
import com.example.WebBanCom.service.OrderDetailService;
import com.example.WebBanCom.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderDetailService orderDetailService;
    @GetMapping("/checkout")
    public String checkout() {
        return "/cart/checkout";
    }
    @PostMapping("/submit")
    public String submitOrder(String customerName, String address, String email, String sdt, String note) {
        List<CartItem> cartItems = cartService.getCartItems();
        if (cartItems.isEmpty()) {
            return "redirect:/cart"; // Redirect if cart is empty
        }
        orderService.createOrder(customerName, address, email, sdt, note, cartItems);
        return "redirect:/order/confirmation";
    }
    @GetMapping("/confirmation")
    public String orderConfirmation(Model model) {
        model.addAttribute("message", "Your order has been successfully placed.");
        return "cart/order-confirmation";
    }
    @GetMapping("/index")
    public String getOrderList(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "/order/index";
    }
    @PostMapping("/delete/{orderId}")
    public String deleteOrder(@PathVariable("orderId") Long orderId) {
        orderService.deleteOrder(orderId);
        return "redirect:/order/index";
    }
}
