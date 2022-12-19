package com.example.springbackend.controller;

import com.example.springbackend.data.dto.HumanDTO;
import com.example.springbackend.facade.Response;
import com.example.springbackend.model.Human;
import com.example.springbackend.service.impl.HumanServiceImpl;
import com.example.springbackend.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/humans")
public class HumanController {
    private HumanServiceImpl humanService;
    private UserServiceImpl userService;

    @Autowired
    public HumanController(HumanServiceImpl humanService, UserServiceImpl userService) {
        this.humanService = humanService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Response<Object>> findAll(HttpServletRequest req) {
        List<Human> people = this.humanService.findAllByUserId(this.userService.findByLogin(req.getHeader("login")).getId());
        return Response.success(people);

    }

    @PostMapping
    public ResponseEntity<Response<Object>> save(@RequestBody HumanDTO humanDao, HttpServletRequest request) {
        this.humanService.save(new Human(
                humanDao.getName(),
                humanDao.getSurname(),
                humanDao.getBirthdayDate(),
                this.userService.findByLogin(request.getHeader("login")).getId(),
                humanDao.getTime(),
                humanDao.getFate()));
        return Response.success();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Object>> update(@RequestBody String fate, @PathVariable Integer id) {
        this.humanService.updateFate(id, fate);
        return Response.success();

    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<Response<Object>> delete(@PathVariable String id) {
        this.humanService.deleteById(Integer.parseInt(id));
        return Response.success();

    }
}
