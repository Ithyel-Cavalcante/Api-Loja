package com.banco_products.products.controllers;

import com.banco_products.products.models.Order;
import com.banco_products.products.models.Bank;
import com.banco_products.products.repositories.OrderRepository;
import com.banco_products.products.repositories.BankRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BankRepository bankRepository;


    @PostMapping(value = "/createOrder", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {

        Bank product = bankRepository.findById(order.getProduct().getId()).orElse(null);

        if (product == null) {
            return ResponseEntity.badRequest().build();
        }


        order.setTotalPrice(product.getPrice() * order.getQuantity());


        Order savedOrder = orderRepository.save(order);
        return ResponseEntity.ok(savedOrder);
    }


    @GetMapping(value = "/listOrder", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


    @GetMapping(value = "/Order/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getOrderById(@PathVariable long id) {
        return orderRepository.findById(id)
                .map(order -> ResponseEntity.ok().body(order))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));

        orderRepository.delete(order);

        return ResponseEntity.ok(order);
    }
}
