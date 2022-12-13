package com.example.springbackend.service;

import com.example.springbackend.model.EventOrder;

import java.util.List;
import java.util.Optional;

public interface EventOrderService {
    EventOrder save(EventOrder order);

    Optional<EventOrder> findById(Integer id);

    List<EventOrder> findAll();

    List<EventOrder> findAllByStatus(String status);

    void deleteById(Integer id);

    void delete(EventOrder entity);
}
