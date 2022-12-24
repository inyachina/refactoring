package com.example.springbackend.service.impl;

import com.example.springbackend.data.dto.ProductDTO;
import com.example.springbackend.model.Product;
import com.example.springbackend.repository.ProductRepository;
import com.example.springbackend.service.ProductService;
import com.example.springbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserService userService;

    @Override
    public Product save(ProductDTO dto, String login) {
        return this.productRepository.save(new Product(dto.getName(),
                dto.getDescription(),
                this.userService.findByLogin(login).getId(),
                dto.getTimeCurrent() / 100));
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
