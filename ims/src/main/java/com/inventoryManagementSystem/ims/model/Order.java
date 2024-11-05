package com.inventoryManagementSystem.ims.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "orderId")  // Explicitly specify the column name
    private String orderId;

    private String customer_name;
    private Integer customer_id;
    private String product_name;
    private String phone;
    private Integer quantity;
    private Double total_price = 0.0;
    private LocalDate order_date = LocalDate.now();
    private String status = "Pending";
}
