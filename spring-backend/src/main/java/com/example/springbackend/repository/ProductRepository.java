package com.example.springbackend.repository;

import com.example.springbackend.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product save(Product product);

    Optional<Product> findById(Integer id);

    List<Product> findAll();

    List<Product> findAllBySold(boolean isSold);

    List<Product> findAll(Sort sort);

    List<Product> findAllById(Iterable<Integer> ids);

    void deleteById(Integer id);

    void delete(Product product);
}
