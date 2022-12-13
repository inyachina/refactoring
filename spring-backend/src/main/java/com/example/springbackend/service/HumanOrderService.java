package com.example.springbackend.service;

import com.example.springbackend.model.Human;
import com.example.springbackend.model.HumanOrder;

import java.util.List;
import java.util.Optional;

public interface HumanOrderService {
    HumanOrder save(HumanOrder order);

    Optional<HumanOrder> findById(Integer id);

    List<Human> findHumanOrderByStatus(String status);

    Optional<HumanOrder> findByHuman_Id(Integer id);

    List<HumanOrder> findAll();

    List<HumanOrder> findAllByStatus(String status);

    void deleteById(Integer id);

    void delete(HumanOrder order);

    public List<Human> findAllHumanInActiveStatus();
}
