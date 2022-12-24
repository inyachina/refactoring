package com.example.springbackend.service;

import com.example.springbackend.data.dto.ProductOrderDTO;
import com.example.springbackend.model.Product;
import com.example.springbackend.model.ProductOrder;

import java.util.List;

public interface ProductOrderService {
    ProductOrder save(ProductOrderDTO order, String login);

    List<Product> findAllByStatus(String in_the_basket, String status);

    void deleteById(Integer id);

}
