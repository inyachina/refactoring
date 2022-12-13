package com.example.springbackend.repository;

import com.example.springbackend.model.Event;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Integer> {
    Event save(Event event);

    Optional<Event> findById(Integer id);

    List<Event> findAll();

    void deleteById(Integer id);

    void delete(Event entity);

}
