package com.example.springbackend.service.impl;

import com.example.springbackend.model.Product;
import com.example.springbackend.repository.ProductRepository;
import com.example.springbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return this.productRepository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }


    @Override
    public List<Product> findAllById(Iterable<Integer> ids) {
        return this.productRepository.findAllById(ids);
    }

    @Override
    public void deleteById(Integer id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public void delete(Product product) {
        this.productRepository.delete(product);
    }

    @Override
    public List<Product> findAllBySold(boolean isSold) {
        return this.productRepository.findAllBySold(isSold);
    }
}
