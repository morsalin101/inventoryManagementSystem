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
    private Long o_id;
    private String customer_name;
    private Integer customer_id;  // Use Integer instead of int
    private String product_name;
    private String phone;
    private Integer quantity;      // Use Integer instead of int
    private Double total_price = 0.0;    // Use Double instead of double
    private LocalDate order_date = LocalDate.now();   // LocalDate allows null by default

    // Add other fields here with wrapper classes if needed
}
