package com.example.springbackend.service.impl;

import com.example.springbackend.model.Human;
import com.example.springbackend.model.HumanOrder;
import com.example.springbackend.repository.EventRepository;
import com.example.springbackend.repository.HumanOrderRepository;
import com.example.springbackend.service.HumanOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HumanOrderServiceImpl implements HumanOrderService {
    private HumanOrderRepository humanOrderRepository;

    @Autowired
    public HumanOrderServiceImpl(HumanOrderRepository humanOrderRepository) {
        this.humanOrderRepository = humanOrderRepository;
    }

    @Override
    public HumanOrder save(HumanOrder order) {
        return this.humanOrderRepository.save(order);
    }

    @Override
    public Optional<HumanOrder> findById(Integer id) {
        return this.humanOrderRepository.findById(id);
    }

    @Override
    public List<Human> findHumanOrderByStatus(String status) {
        return this.humanOrderRepository.findHumanOrderByStatus(status);
    }

    @Override
    public Optional<HumanOrder> findByHuman_Id(Integer id) {
        return this.humanOrderRepository.findByHuman_Id(id);
    }

    @Override
    public List<HumanOrder> findAll() {
        return this.humanOrderRepository.findAll();
    }

    @Override
    public List<HumanOrder> findAllByStatus(String status) {
        return this.humanOrderRepository.findAllByStatus(status);
    }

    @Override
    public void deleteById(Integer id) {
        this.humanOrderRepository.deleteById(id);
    }

    @Override
    public void delete(HumanOrder order) {
        this.humanOrderRepository.delete(order);
    }

    @Override
    public List<Human> findAllHumanInActiveStatus() {
        return  null;
    }
}
