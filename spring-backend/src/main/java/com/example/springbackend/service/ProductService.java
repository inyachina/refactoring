package com.example.springbackend.service;

import com.example.springbackend.model.Product;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product save(Product product);

    Optional<Product> findById(Integer id);

    List<Product> findAll();

    List<Product> findAllById(Iterable<Integer> ids);

    void deleteById(Integer id);

    void delete(Product product);
    List<Product> findAllBySold (boolean isSold);
}
