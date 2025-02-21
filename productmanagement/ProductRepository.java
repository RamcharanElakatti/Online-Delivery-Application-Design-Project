package com.OnlineDeliveryApplication.productmanagement;

import java.util.List;


import com.OnlineDeliveryApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);
    List<Product> findByVendor(User vendor);
}