package com.example.springbackend.service.impl;

import com.example.springbackend.model.ProductOrder;
import com.example.springbackend.repository.ProductOrderRepository;
import com.example.springbackend.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {
    private ProductOrderRepository productOrderRepository;

    @Autowired
    public ProductOrderServiceImpl(ProductOrderRepository productOrderRepository) {
        this.productOrderRepository = productOrderRepository;
    }

    @Override
    public ProductOrder save(ProductOrder order) {
        return this.productOrderRepository.save(order);
    }

    @Override
    public Optional<ProductOrder> findById(Integer id) {
        return this.productOrderRepository.findById(id);
    }

    @Override
    public List<ProductOrder> findAll() {
        return productOrderRepository.findAll();
    }

    @Override
    public List<ProductOrder> findAllByStatus(String status) {
        return productOrderRepository.findAllByStatus(status);
    }

    @Override
    public void deleteById(Integer id) {
        this.productOrderRepository.deleteById(id);
    }

    @Override
    public void delete(ProductOrder order) {
        this.productOrderRepository.deleteById(order.getId());
    }
}
