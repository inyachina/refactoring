package com.example.springbackend.controller;

import com.example.springbackend.data.dto.EventDTO;
import com.example.springbackend.facade.Response;
import com.example.springbackend.model.Event;
import com.example.springbackend.service.impl.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/events")
public class EventController {
    private EventServiceImpl eventService;

    @Autowired
    public EventController(EventServiceImpl eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<Response<Object>> findAll() {
        List<Event> events = this.eventService.findAll();
        return Response.success(events);
    }

    @PostMapping
    public ResponseEntity<Response<Object>> save(@RequestBody EventDTO eventDTO) {

        this.eventService.save(new Event(eventDTO.getName(), eventDTO.getDescription(), Date.valueOf(LocalDate.now()),
                eventDTO.getStartTime(), eventDTO.getEndTime()));
        return Response.success();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Object>> update(@RequestBody EventDTO eventDTO) {

        this.eventService.update(new Event(eventDTO.getName(), eventDTO.getDescription(), Date.valueOf(LocalDate.now()),
                eventDTO.getStartTime(), eventDTO.getEndTime()));
        return Response.success();

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Object>> delete(@PathVariable String id) {
        this.eventService.deleteById(Integer.parseInt(id));
        return Response.success();

    }
}
