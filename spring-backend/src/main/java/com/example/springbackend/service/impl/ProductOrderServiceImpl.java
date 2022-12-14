package com.example.springbackend.service.impl;

import com.example.springbackend.data.dto.ProductOrderDTO;
import com.example.springbackend.exception.NotFoundException;
import com.example.springbackend.model.Product;
import com.example.springbackend.model.ProductOrder;
import com.example.springbackend.model.User;
import com.example.springbackend.repository.ProductOrderRepository;
import com.example.springbackend.repository.ProductRepository;
import com.example.springbackend.service.ProductOrderService;
import com.example.springbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductOrderServiceImpl implements ProductOrderService {
    private ProductOrderRepository productOrderRepository;
    private ProductRepository productRepository;
    private UserService userService;

    @Transactional
    @Override
    public ProductOrder save(ProductOrderDTO dto, String login) {
        User user = this.userService.findByLogin(login);
        ProductOrder productOrder = this.productOrderRepository.save(new ProductOrder(
                "IN_THE_BASKET",
                user.getId(),
                dto.getProductId(),
                user.getId(),
                dto.getFromTime(),
                dto.getToTime()));

        return this.productRepository.findById(dto.getProductId()).map(product -> {
                    product.setSold(true);
                    this.productRepository.save(product);
                    return this.productOrderRepository.save(productOrder);
                }
        ).orElseThrow(NotFoundException::new);

    }

    @Override
    public List<Product> findAllByStatus(String status, String login) {
        int id = this.userService.findByLogin(login).getId();
        List<ProductOrder> orders = this.productOrderRepository.findAllByStatus(status)
                .stream().filter(productOrder -> productOrder.getFromUser() == id).toList();
        return this.productRepository.findAllById(orders.stream().map(ProductOrder::getProductId).toList());

    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        this.productOrderRepository.findById(id).ifPresent(productOrder -> {
            this.productOrderRepository.deleteById(id);
            this.productRepository.findById(productOrder.getProductId()).ifPresent(product -> {
                product.setSold(false);
                this.productRepository.save(product);
            });
        });
    }
}
