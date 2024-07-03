package com.example.WebBanCom.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

 //   @Column(name = "userId")
 //   private Long userId; // Thêm cột userId để lưu trữ id của User
 //   private List<User> users;
    private String customerName;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

    private String address; // Trường mới: Địa chỉ
    private String email; // Trường mới: Email
    private String sdt; // Trường mới: Số điện thoại
    private String note; // Trường mới: Ghi chú
}