package com.example.springbackend.service.impl;

import com.example.springbackend.model.Event;
import com.example.springbackend.repository.EventRepository;
import com.example.springbackend.service.EventService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event save(Event event) {
        return this.eventRepository.save(event);
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
    public void update(@NonNull Event newEvent) {
        Event oldEvent = this.eventRepository.findById(newEvent.getId()).get();
        oldEvent.setName(newEvent.getName());
        oldEvent.setDescription(newEvent.getDescription());
        oldEvent.setStartTime(newEvent.getStartTime());
        oldEvent.setEndTime(newEvent.getEndTime());
        this.eventRepository.save(oldEvent);
    }

}
