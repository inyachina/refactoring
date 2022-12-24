package com.example.springbackend.service;

import com.example.springbackend.data.dto.ProductDTO;
import com.example.springbackend.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Optional<Product> findById(Integer id);

    List<Product> findAll();

    Product save(ProductDTO product, String login);

    void deleteById(Integer id);

    void delete(Product product);

    List<Product> findAllBySold(boolean isSold);

}
