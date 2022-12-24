package com.example.springbackend.repository;

import com.example.springbackend.model.EventOrder;
import com.example.springbackend.model.Human;
import com.example.springbackend.model.HumanOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HumanOrderRepository extends JpaRepository<HumanOrder, Integer> {
    HumanOrder save(HumanOrder order);

    Optional<HumanOrder> findById(Integer id);

    Optional<HumanOrder> findByHumanId(Integer id);

    List<Human> findHumanOrderByStatus(String status);

    List<HumanOrder> findAllByStatus(String status);

    void deleteById(Integer id);

    void delete(HumanOrder order);
}
