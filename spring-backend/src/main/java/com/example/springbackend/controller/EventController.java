package com.example.springbackend.controller;

import com.example.springbackend.dao.EventDao;
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
@RequestMapping("api/event")
@CrossOrigin(origins = "http://localhost:4200")
public class EventController {
    private EventServiceImpl eventService;

    @Autowired
    public EventController(EventServiceImpl eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/all")
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
    @PostMapping("/create")
    public ResponseEntity<Response<Object>> save(@RequestBody EventDao eventDao) {
        try {
            this.eventService.save(new Event(eventDao.getName(), eventDao.getDescription(), Date.valueOf(LocalDate.now()),
                    eventDao.getStartTime(), eventDao.getEndTime()));
            return Response.success();
        }catch (Exception e){
            return Response.failure(
                    Response.error(
                            new ErrorBody(e.getMessage(),
                                    HttpStatus.INTERNAL_SERVER_ERROR, Arrays.toString(e.getStackTrace()))));
        }
    }
    @PutMapping("/update")
    public ResponseEntity<Response<Object>> update(@RequestBody EventDao eventDao) {
        try {
            this.eventService.update(new Event(eventDao.getName(), eventDao.getDescription(), Date.valueOf(LocalDate.now()),
                    eventDao.getStartTime(), eventDao.getEndTime()));
            return Response.success();
        }catch (Exception e){
            return Response.failure(
                    Response.error(
                            new ErrorBody(e.getMessage(),
                                    HttpStatus.INTERNAL_SERVER_ERROR, Arrays.toString(e.getStackTrace()))));
        }
    }


    @DeleteMapping("delete/{id}")
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
