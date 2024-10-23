
package com.inventoryManagementSystem.ims.repository;

import com.inventoryManagementSystem.ims.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}