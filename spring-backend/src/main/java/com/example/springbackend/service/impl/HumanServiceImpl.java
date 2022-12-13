package com.example.springbackend.service.impl;

import com.example.springbackend.model.Human;
import com.example.springbackend.repository.HumanRepository;
import com.example.springbackend.service.HumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HumanServiceImpl implements HumanService {
    private HumanRepository humanRepository;

    @Autowired
    public HumanServiceImpl(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }

    @Override
    public Human save(Human human) {
        return this.humanRepository.save(human);
    }

    @Override
    public Optional<Human> findById(Integer id) {
        return this.humanRepository.findById(id);
    }

    @Override
    public List<Human> findAll() {
        return this.humanRepository.findAll();
    }

    @Override
    public List<Human> findAllByUserId(Integer id) {
        return this.humanRepository.findAllByUserId(id);
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
    public void updateFate(Integer id, String fate) {
        Human oldHuman = this.humanRepository.findById(id).get();
        oldHuman.setFate(fate);
        this.humanRepository.save(oldHuman);
    }

    @Override
    public List<Human> findAllById(Iterable<Integer> ids) {
        return this.humanRepository.findAllById(ids);
    }
}
