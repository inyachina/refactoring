package com.example.springbackend.controller;

import com.example.springbackend.dao.ProductOrderDao;
import com.example.springbackend.facade.ErrorBody;
import com.example.springbackend.facade.Response;
import com.example.springbackend.model.Human;
import com.example.springbackend.model.HumanOrder;
import com.example.springbackend.model.Product;
import com.example.springbackend.model.ProductOrder;
import com.example.springbackend.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/orders")
@CrossOrigin(origins = "http://localhost:4200")
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

    @PostMapping("/human-fate")
    public ResponseEntity<Response<Object>> saveHumanFateOrder(@PathVariable Integer id, HttpServletRequest req) {
        try {
            this.humanOrderService.save(new HumanOrder(
                    "IN_PROGRESS",
                    this.userService.findByLogin(req.getHeader("login")).getId(),
                    this.humanService.findById(id).get()));
            return Response.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.failure(
                    Response.error(
                            new ErrorBody(e.getMessage(),
                                    HttpStatus.INTERNAL_SERVER_ERROR, Arrays.toString(e.getStackTrace()))));
        }
    }

    @GetMapping("/human-fate/{id}")
    public ResponseEntity<Response<Object>> findHumanFateOrder(@PathVariable Integer id) {
        try {
            Optional<HumanOrder> humanOrder = this.humanOrderService.findByHuman_Id(id);
            if (humanOrder.isPresent()) return Response.success(humanOrder);
            else return Response.success(null);
        } catch (Exception e) {
            return Response.failure(
                    Response.error(
                            new ErrorBody(e.getMessage(),
                                    HttpStatus.INTERNAL_SERVER_ERROR, Arrays.toString(e.getStackTrace()))));
        }
    }

    @GetMapping("/human-fate/active")
    public ResponseEntity<Response<Object>> findAllActiveHumanOrders() {
        try {
            List<HumanOrder> humanOrders = this.humanOrderService.findAllByStatus("IN_PROGRESS");
            return Response.success(this.humanService.findAllById(humanOrders.stream().map(HumanOrder::getId).toList()));
        } catch (Exception e) {
            return Response.failure(
                    Response.error(
                            new ErrorBody(e.getMessage(),
                                    HttpStatus.INTERNAL_SERVER_ERROR, Arrays.toString(e.getStackTrace()))));
        }
    }

    @PutMapping("/human-fate/accept/{id}")
    public ResponseEntity<Response<Object>> acceptHumanFateOrder(@PathVariable Integer id, @RequestBody String fate) {
        try {
            HumanOrder humanOrder = this.humanOrderService.findByHuman_Id(id).get();
            humanOrder.setStatus("ACCEPTED");
            Human human = this.humanService.findById(id).get();
            human.setFate(fate);
            this.humanService.save(human);
            return Response.success();
        } catch (Exception e) {
            return Response.failure(
                    Response.error(
                            new ErrorBody(e.getMessage(),
                                    HttpStatus.INTERNAL_SERVER_ERROR, Arrays.toString(e.getStackTrace()))));
        }
    }

    @PostMapping("/product")
    public ResponseEntity<Response<Object>> saveProductOrder(@RequestBody ProductOrderDao dao, HttpServletRequest req) {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            return Response.failure(
                    Response.error(
                            new ErrorBody(e.getMessage(),
                                    HttpStatus.INTERNAL_SERVER_ERROR, Arrays.toString(e.getStackTrace()))));
        }
    }

    @GetMapping("/product")
    public ResponseEntity<Response<Object>> findAllProductOrders(HttpServletRequest request) {
        try {
            int id = this.userService.findByLogin(request.getHeader("login")).getId();
            List<ProductOrder> orders = this.productOrderService.findAllByStatus("IN_THE_BASKET")
                    .stream().filter(productOrder -> productOrder.getFromUser() == id).toList();
            return Response.success(this.productService.findAllById(orders.stream().map(ProductOrder::getProductId).toList()));
        } catch (Exception e) {
            return Response.failure(
                    Response.error(
                            new ErrorBody(e.getMessage(),
                                    HttpStatus.INTERNAL_SERVER_ERROR, Arrays.toString(e.getStackTrace()))));
        }
    }

    @DeleteMapping("/product/basket/{orderId}")
    public ResponseEntity<Response<Object>> deleteProductOrder(@PathVariable Integer orderId, HttpServletRequest req) {
        try {
            int productId = this.productOrderService.findById(orderId).get().getProductId();
            this.productOrderService.deleteById(orderId);
            Product product = this.productService.findById(productId).get();
            product.setSold(false);
            this.productService.save(product);
            return Response.success();
        } catch (Exception e) {
            return Response.failure(
                    Response.error(
                            new ErrorBody(e.getMessage(),
                                    HttpStatus.INTERNAL_SERVER_ERROR, Arrays.toString(e.getStackTrace()))));
        }
    }
}
