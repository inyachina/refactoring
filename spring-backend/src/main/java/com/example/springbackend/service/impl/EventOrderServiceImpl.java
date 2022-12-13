package com.example.springbackend.service.impl;

import com.example.springbackend.model.EventOrder;
import com.example.springbackend.repository.EventOrderRepository;
import com.example.springbackend.service.EventOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventOrderServiceImpl implements EventOrderService {
    private EventOrderRepository eventOrderRepository;

    @Autowired
    public EventOrderServiceImpl(EventOrderRepository eventOrderRepository) {
        this.eventOrderRepository = eventOrderRepository;
    }

    @Override
    public EventOrder save(EventOrder order) {
        return this.eventOrderRepository.save(order);
    }

    @Override
    public Optional<EventOrder> findById(Integer id) {
        return this.eventOrderRepository.findById(id);
    }

    @Override
    public List<EventOrder> findAll() {
        return this.eventOrderRepository.findAll();
    }

    @Override
    public List<EventOrder> findAllByStatus(String status) {
        return this.eventOrderRepository.findAllByStatus(status);
    }

    @Override
    public void deleteById(Integer id) {
        this.eventOrderRepository.deleteById(id);
    }

    @Override
    public void delete(EventOrder eventOrder) {
        this.eventOrderRepository.delete(eventOrder);
    }
}
