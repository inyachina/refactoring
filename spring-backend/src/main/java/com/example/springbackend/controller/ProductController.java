package com.example.springbackend.controller;

import com.example.springbackend.data.dto.ProductDTO;
import com.example.springbackend.model.Product;
import com.example.springbackend.service.impl.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/products")
@AllArgsConstructor
public class ProductController {
    private final ProductServiceImpl productService;

    @GetMapping("/active")
    public ResponseEntity findAllActiveProducts() {
        List<Product> products = this.productService.findAllBySold(false);
        if (products.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity saveProduct(@RequestBody ProductDTO product, HttpServletRequest req) {
        return ResponseEntity.ok(this.productService.save(product, req.getHeader("login")));
    }
}
