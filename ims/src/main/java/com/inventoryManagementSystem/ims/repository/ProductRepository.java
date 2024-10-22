package com.inventoryManagementSystem.ims.repository;
import com.inventoryManagementSystem.ims.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
