package com.inventoryManagementSystem.ims.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "orders")  // Ensure the table name matches exactly with your database
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long o_id;


    private String customer_name;

    
    private String product_name;

  
    private String phone;

  
    private int quantity;

  
    private LocalDate order_date;

    // Getters and setters omitted for brevity
}
