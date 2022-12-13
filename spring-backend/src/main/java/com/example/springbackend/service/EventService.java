package com.example.springbackend.service;

import com.example.springbackend.model.Event;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface EventService {
    Event save(Event event);

    Optional<Event> findById(Integer id);

    List<Event> findAll();

    void deleteById(Integer id);

    void delete(Event entity);

    void update(Event event);
}
