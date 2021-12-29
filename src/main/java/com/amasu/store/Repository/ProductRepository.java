package com.amasu.store.Repository;

import com.amasu.store.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Integer deleteByProductName(String productName);

    List<Product> findAllByType(String type);
}
