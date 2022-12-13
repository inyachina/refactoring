package com.example.springbackend.repository;

import com.example.springbackend.model.EventOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventOrderRepository extends JpaRepository<EventOrder, Integer> {
    EventOrder save(EventOrder order);

    Optional<EventOrder> findById(Integer id);

    List<EventOrder> findAll();

    List<EventOrder> findAllByStatus(String status);

    void deleteById(Integer id);

    void delete(EventOrder entity);
}
