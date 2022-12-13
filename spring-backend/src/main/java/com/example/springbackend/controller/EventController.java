package com.example.springbackend.controller;

import com.example.springbackend.data.dto.EventDTO;
import com.example.springbackend.facade.ErrorBody;
import com.example.springbackend.facade.Response;
import com.example.springbackend.model.Event;
import com.example.springbackend.service.impl.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
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
        try {
            List<Event> events = this.eventService.findAll();
            return Response.success(events);
        }catch (Exception e){
            return Response.failure(
                    Response.error(
                            new ErrorBody(e.getMessage(),
                                    HttpStatus.INTERNAL_SERVER_ERROR, Arrays.toString(e.getStackTrace()))));
        }
    }
    @PostMapping
    public ResponseEntity<Response<Object>> save(@RequestBody EventDTO eventDTO) {
        try {
            this.eventService.save(new Event(eventDTO.getName(), eventDTO.getDescription(), Date.valueOf(LocalDate.now()),
                    eventDTO.getStartTime(), eventDTO.getEndTime()));
            return Response.success();
        }catch (Exception e){
            return Response.failure(
                    Response.error(
                            new ErrorBody(e.getMessage(),
                                    HttpStatus.INTERNAL_SERVER_ERROR, Arrays.toString(e.getStackTrace()))));
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Response<Object>> update(@RequestBody EventDTO eventDTO) {
        try {
            this.eventService.update(new Event(eventDTO.getName(), eventDTO.getDescription(), Date.valueOf(LocalDate.now()),
                    eventDTO.getStartTime(), eventDTO.getEndTime()));
            return Response.success();
        }catch (Exception e){
            return Response.failure(
                    Response.error(
                            new ErrorBody(e.getMessage(),
                                    HttpStatus.INTERNAL_SERVER_ERROR, Arrays.toString(e.getStackTrace()))));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Object>> delete(@PathVariable String id) {
        try {
            this.eventService.deleteById(Integer.parseInt(id));
            return Response.success();
        }catch (Exception e){
            return Response.failure(
                    Response.error(
                            new ErrorBody(e.getMessage(),
                                    HttpStatus.INTERNAL_SERVER_ERROR, Arrays.toString(e.getStackTrace()))));
        }
    }
}
