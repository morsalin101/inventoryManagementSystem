package com.inventoryManagementSystem.ims.repository;

import com.inventoryManagementSystem.ims.model.Category; // Ensure this path is correct or update it to the correct package
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
