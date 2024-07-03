package com.example.WebBanCom.service;

import com.example.WebBanCom.model.CartItem;
import com.example.WebBanCom.model.Order;
import com.example.WebBanCom.model.OrderDetail;
import com.example.WebBanCom.repository.OrderDetailRepository;
import com.example.WebBanCom.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private CartService cartService; // Assuming you have a CartService
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    @Transactional
    public Order createOrder(String customerName, String address, String email, String sdt, String note, List<CartItem> cartItems) {
        Order order = new Order();
        order.setCustomerName(customerName);
        order.setAddress(address);
        order.setEmail(email);
        order.setSdt(sdt);
        order.setNote(note);
        order = orderRepository.save(order);
        for (CartItem item : cartItems) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setProduct(item.getProduct());
            detail.setQuantity(item.getQuantity());
            orderDetailRepository.save(detail);
        }
        // Optionally clear the cart after order placement
        cartService.clearCart();
        return order;
    }
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            List<OrderDetail> orderDetails = orderDetailRepository.findByOrder(order);
            orderDetailRepository.deleteAll(orderDetails);
            orderRepository.delete(order);
        }
    }
}
