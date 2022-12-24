package com.example.springbackend.controller;

import com.example.springbackend.data.dto.EventDTO;
import com.example.springbackend.model.Event;
import com.example.springbackend.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/events")
@AllArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping
    public ResponseEntity findAll() {
        List<Event> events = this.eventService.findAll();
        if (events.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(events);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody EventDTO eventDTO) {
        return ResponseEntity.ok(this.eventService.save(eventDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody Event event) {
        return ResponseEntity.ok(this.eventService.update(event, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        this.eventService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
