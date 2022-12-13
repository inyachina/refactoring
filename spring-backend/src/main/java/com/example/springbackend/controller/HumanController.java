package com.example.springbackend.controller;

import com.example.springbackend.data.dto.HumanDTO;
import com.example.springbackend.facade.ErrorBody;
import com.example.springbackend.facade.Response;
import com.example.springbackend.model.Human;
import com.example.springbackend.service.impl.HumanServiceImpl;
import com.example.springbackend.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/human")
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
        try {
            List<Human> people = this.humanService.findAllByUserId(this.userService.findByLogin(req.getHeader("login")).getId());
            return Response.success(people);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.failure(
                    Response.error(
                            new ErrorBody(e.getMessage(),
                                    HttpStatus.INTERNAL_SERVER_ERROR, Arrays.toString(e.getStackTrace()))));
        }
    }

    @PostMapping
    public ResponseEntity<Response<Object>> save(@RequestBody HumanDTO humanDao, HttpServletRequest request) {
        try {
            this.humanService.save(new Human(
                    humanDao.getName(),
                    humanDao.getSurname(),
                    humanDao.getBirthdayDate(),
                    this.userService.findByLogin(request.getHeader("login")).getId(),
                    humanDao.getTime(),
                    humanDao.getFate()));
            return Response.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.failure(
                    Response.error(
                            new ErrorBody(e.getMessage(),
                                    HttpStatus.INTERNAL_SERVER_ERROR, Arrays.toString(e.getStackTrace()))));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Object>> update(@RequestBody String fate, @PathVariable Integer id) {
        try {
            this.humanService.updateFate(id, fate);
            return Response.success();
        } catch (Exception e) {
            return Response.failure(
                    Response.error(
                            new ErrorBody(e.getMessage(),
                                    HttpStatus.INTERNAL_SERVER_ERROR, Arrays.toString(e.getStackTrace()))));
        }
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<Response<Object>> delete(@PathVariable String id) {
        try {
            this.humanService.deleteById(Integer.parseInt(id));
            return Response.success();
        } catch (Exception e) {
            return Response.failure(
                    Response.error(
                            new ErrorBody(e.getMessage(),
                                    HttpStatus.INTERNAL_SERVER_ERROR, Arrays.toString(e.getStackTrace()))));
        }
    }
}
