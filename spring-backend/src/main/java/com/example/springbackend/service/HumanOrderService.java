package com.example.springbackend.service;

import com.example.springbackend.model.Human;
import com.example.springbackend.model.HumanOrder;

import java.util.List;
import java.util.Optional;

public interface HumanOrderService {
    HumanOrder save(Integer humanId, String login);

    Optional<HumanOrder> findById(Integer id);

    HumanOrder acceptHumanFateOrder(Integer id, String fate);

    List<HumanOrder> findAll();

    List<HumanOrder> findAllByStatus(String status);

    void deleteById(Integer id);

    void delete(HumanOrder order);

}
