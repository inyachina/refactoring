package com.example.springbackend.service;

import com.example.springbackend.data.dto.EventDTO;
import com.example.springbackend.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    Event save(EventDTO event);

    Optional<Event> findById(Integer id);

    List<Event> findAll();

    void deleteById(Integer id);

    void delete(Event entity);

    Event update(Event event, Integer id);
}
