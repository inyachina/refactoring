package com.example.springbackend.service.impl;

import com.example.springbackend.data.dto.HumanDTO;
import com.example.springbackend.exception.NotFoundException;
import com.example.springbackend.model.Human;
import com.example.springbackend.repository.HumanRepository;
import com.example.springbackend.service.HumanService;
import com.example.springbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HumanServiceImpl implements HumanService {
    private final HumanRepository humanRepository;
    private final UserService userService;

    @Override
    public Human save(HumanDTO dto, String login) {
        return this.humanRepository.save(new Human(
                dto.getName(),
                dto.getSurname(),
                dto.getBirthdayDate(),
                this.userService.findByLogin(login).getId(),
                dto.getTime(),
                dto.getFate()));
    }

    @Override
    public Human save(Human human) {
        return this.humanRepository.save(human);
    }

    @Override
    public Human findById(Integer id) {
        return this.humanRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Human> findAll() {
        return this.humanRepository.findAll();
    }

    @Override
    public List<Human> findAllByUsername(String login) {
        return this.humanRepository.findAllByUserId(this.userService.findByLogin(login).getId());
    }

    @Override
    public void deleteById(Integer id) {
        this.humanRepository.deleteById(id);
    }

    @Override
    public void delete(Human human) {
        this.humanRepository.deleteById(human.getId());
    }

    @Override
    public Human updateFate(Integer id, String fate) {
        Human oldHuman = this.humanRepository.findById(id).get();
        oldHuman.setFate(fate);
        return this.humanRepository.save(oldHuman);
    }

    @Override
    public List<Human> findAllById(Iterable<Integer> ids) {
        return this.humanRepository.findAllById(ids);
    }


}
