package com.example.WebBanCom.service;

import com.example.WebBanCom.model.OrderDetail;
import com.example.WebBanCom.repository.OrderDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailService(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailRepository.findAll();
    }
}
