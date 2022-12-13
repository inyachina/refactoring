package com.example.springbackend.repository;

import com.example.springbackend.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer> {
    ProductOrder save(ProductOrder order);

    Optional<ProductOrder> findById(Integer id);

    List<ProductOrder> findAll();

    List<ProductOrder> findAllByStatus(String status);

    void deleteById(Integer id);

    void delete(ProductOrder order);
}
