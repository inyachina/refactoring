package com.example.springbackend.controller;

import com.example.springbackend.data.dto.ProductOrderDTO;
import com.example.springbackend.facade.Response;
import com.example.springbackend.model.Human;
import com.example.springbackend.model.HumanOrder;
import com.example.springbackend.model.Product;
import com.example.springbackend.model.ProductOrder;
import com.example.springbackend.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/orders")
public class OrderController {
    private ProductOrderServiceImpl productOrderService;
    private EventOrderServiceImpl eventOrderService;
    private HumanOrderServiceImpl humanOrderService;
    private UserServiceImpl userService;
    private HumanServiceImpl humanService;
    private ProductServiceImpl productService;

    @Autowired
    public OrderController(ProductOrderServiceImpl productOrderService, EventOrderServiceImpl eventOrderService,
                           HumanOrderServiceImpl humanOrderService, UserServiceImpl userService,
                           HumanServiceImpl humanService, ProductServiceImpl productService) {
        this.productOrderService = productOrderService;
        this.eventOrderService = eventOrderService;
        this.humanOrderService = humanOrderService;
        this.userService = userService;
        this.humanService = humanService;
        this.productService = productService;
    }

    @PostMapping("/human-fate/{id}")
    public ResponseEntity<Response<Object>> saveHumanFateOrder(@PathVariable Integer id, HttpServletRequest req) {
        this.humanOrderService.save(new HumanOrder(
                "IN_PROGRESS",
                this.userService.findByLogin(req.getHeader("login")).getId(),
                this.humanService.findById(id).get()));
        return Response.success();
    }

    @GetMapping("/human-fate/{id}")
    public ResponseEntity<Response<Object>> findHumanFateOrder(@PathVariable Integer id) {
        Optional<HumanOrder> humanOrder = this.humanOrderService.findByHuman_Id(id);
        if (humanOrder.isPresent()) return Response.success(humanOrder);
        else return Response.success(null);
    }

    @GetMapping("/human-fate/active")
    public ResponseEntity<Response<Object>> findAllActiveHumanOrders() {
        List<HumanOrder> humanOrders = this.humanOrderService.findAllByStatus("IN_PROGRESS");
        return Response.success(this.humanService.findAllById(humanOrders.stream().map(HumanOrder::getId).toList()));
    }

    @PutMapping("/human-fate/accept/{id}")
    public ResponseEntity<Response<Object>> acceptHumanFateOrder(@PathVariable Integer id, @RequestBody String fate) {
        HumanOrder humanOrder = this.humanOrderService.findByHuman_Id(id).get();
        humanOrder.setStatus("ACCEPTED");
        Human human = this.humanService.findById(id).get();
        human.setFate(fate);
        this.humanService.save(human);
        return Response.success();
    }

    @PostMapping("/product")
    public ResponseEntity<Response<Object>> saveProductOrder(@RequestBody ProductOrderDTO dao, HttpServletRequest req) {
        this.productOrderService.save(new ProductOrder(
                "IN_THE_BASKET",
                this.userService.findByLogin(req.getHeader("login")).getId(),
                dao.getProductId(),
                this.userService.findByLogin(dao.getToUser()).getId(),
                dao.getFromTime(),
                dao.getToTime()));

        Product product = this.productService.findById(dao.getProductId()).get();
        product.setSold(true);
        this.productService.save(product);
        return Response.success();
    }

    @GetMapping("/product")
    public ResponseEntity<Response<Object>> findAllProductOrders(HttpServletRequest request) {
        int id = this.userService.findByLogin(request.getHeader("login")).getId();
        List<ProductOrder> orders = this.productOrderService.findAllByStatus("IN_THE_BASKET")
                .stream().filter(productOrder -> productOrder.getFromUser() == id).toList();
        return Response.success(this.productService.findAllById(orders.stream().map(ProductOrder::getProductId).toList()));

    }

    @DeleteMapping("/product/basket/{orderId}")
    public ResponseEntity<Response<Object>> deleteProductOrder(@PathVariable Integer orderId, HttpServletRequest req) {
        int productId = this.productOrderService.findById(orderId).get().getProductId();
        this.productOrderService.deleteById(orderId);
        Product product = this.productService.findById(productId).get();
        product.setSold(false);
        this.productService.save(product);
        return Response.success();

    }
}
