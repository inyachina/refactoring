package com.example.springbackend.controller;

import com.example.springbackend.data.dto.HumanDTO;
import com.example.springbackend.model.Human;
import com.example.springbackend.service.HumanService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/humans")
@AllArgsConstructor
public class HumanController {
    private final HumanService humanService;

    @GetMapping
    public ResponseEntity findAll(HttpServletRequest req) {
        List<Human> people = this.humanService.findAllByUsername(req.getHeader("login"));
        if (people.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(people);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody HumanDTO dto, HttpServletRequest request) {
        return ResponseEntity.ok(this.humanService.save(dto, request.getHeader("login")));
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody String fate, @PathVariable Integer id) {
        return ResponseEntity.ok(this.humanService.updateFate(id, fate));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        this.humanService.deleteById(id);
        return ResponseEntity.ok().build();

    }
}
