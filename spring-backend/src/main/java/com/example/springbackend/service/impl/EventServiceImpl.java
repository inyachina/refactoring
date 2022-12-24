package com.example.springbackend.service.impl;

import com.example.springbackend.data.dto.EventDTO;
import com.example.springbackend.model.Event;
import com.example.springbackend.repository.EventRepository;
import com.example.springbackend.service.EventService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event save(EventDTO dto) {
        return this.eventRepository.save(new Event(dto.getName(), dto.getDescription(), Date.valueOf(LocalDate.now()),
                dto.getStartTime(), dto.getEndTime()));
    }

    @Override
    public Optional<Event> findById(Integer id) {
        return this.eventRepository.findById(id);
    }

    @Override
    public List<Event> findAll() {
        return this.eventRepository.findAll();
    }


    @Override
    public void deleteById(Integer id) {
        this.eventRepository.deleteById(id);
    }

    @Override
    public void delete(Event entity) {
        this.eventRepository.delete(entity);
    }

    @Override
    public Event update(@NonNull Event newEvent, Integer id) {
        newEvent.setId(id);
        return this.eventRepository.save(newEvent);
    }

}
