package com.example.springbackend.service;

import com.example.springbackend.model.ProductOrder;

import java.util.List;
import java.util.Optional;

public interface ProductOrderService {
    ProductOrder save(ProductOrder order);

    Optional<ProductOrder> findById(Integer id);

    List<ProductOrder> findAll();

    List<ProductOrder> findAllByStatus(String status);

    void deleteById(Integer id);

    void delete(ProductOrder order);
}
