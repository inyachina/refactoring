package com.example.springbackend.controller;

import com.example.springbackend.data.dto.ProductDTO;
import com.example.springbackend.facade.Response;
import com.example.springbackend.model.Product;
import com.example.springbackend.service.impl.ProductServiceImpl;
import com.example.springbackend.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {
    private ProductServiceImpl productService;
    private UserServiceImpl userService;

    @Autowired
    public ProductController(ProductServiceImpl productService, UserServiceImpl userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/active")
    public ResponseEntity<Response<Object>> findAllActiveProducts() {
        List<Product> products = this.productService.findAllBySold(false);
        return Response.success(products);
    }

    @PostMapping
    public ResponseEntity<Response<Object>> saveProduct(@RequestBody ProductDTO product, HttpServletRequest req) {
        this.productService.save(new Product(product.getName(),
                product.getDescription(),
                this.userService.findByLogin(req.getHeader("login")).getId(),
                product.getTimeCurrent() / 100));
        return Response.success();
    }
}
