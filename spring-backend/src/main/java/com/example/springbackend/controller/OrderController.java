package com.example.springbackend.controller;

import com.example.springbackend.data.dto.ProductOrderDTO;
import com.example.springbackend.model.HumanOrder;
import com.example.springbackend.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/orders")
@AllArgsConstructor
public class OrderController {
    private final ProductOrderService productOrderService;
    private final HumanOrderService humanOrderService;
    private final HumanService humanService;

    @PostMapping("/human-fate/{id}")
    public ResponseEntity saveHumanFateOrder(@PathVariable Integer humanId, HttpServletRequest req) {
        return ResponseEntity.ok(this.humanOrderService.save(humanId, req.getHeader("login")));
    }

    @GetMapping("/human-fate/{id}")
    public ResponseEntity findHumanFateOrder(@PathVariable Integer id) {
        Optional<HumanOrder> humanOrder = this.humanOrderService.findById(id);
        if (humanOrder.isPresent()) return ResponseEntity.internalServerError().build();
        else return ResponseEntity.ok(humanOrder);
    }

    @GetMapping("/human-fate/active")
    public ResponseEntity findAllActiveHumanOrders() {
        List<HumanOrder> humanOrders = this.humanOrderService.findAllByStatus("IN_PROGRESS");
        return ResponseEntity.ok(this.humanService.findAllById(humanOrders.stream().map(HumanOrder::getId).toList()));
    }

    @PutMapping("/human-fate/accept/{id}")
    public ResponseEntity acceptHumanFateOrder(@PathVariable Integer id, @RequestBody String fate) {
        return ResponseEntity.ok(this.humanOrderService.acceptHumanFateOrder(id, fate));
    }

    @PostMapping("/product")
    public ResponseEntity saveProductOrder(@RequestBody ProductOrderDTO dto, HttpServletRequest req) {
        return ResponseEntity.ok(this.productOrderService.save(dto, req.getHeader("login")));
    }

    @GetMapping("/product")
    public ResponseEntity findAllProductOrders(HttpServletRequest request) {
        return ResponseEntity.ok(this.productOrderService
                .findAllByStatus("IN_THE_BASKET", request.getHeader("login")));
    }

    @DeleteMapping("/product/basket/{orderId}")
    public ResponseEntity deleteProductOrder(@PathVariable Integer orderId) {
        this.productOrderService.deleteById(orderId);
        return ResponseEntity.ok().build();

    }
}
