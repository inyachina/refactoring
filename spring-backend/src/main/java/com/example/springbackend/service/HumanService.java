package com.example.springbackend.service;

import com.example.springbackend.model.Human;

import java.util.List;
import java.util.Optional;

public interface HumanService {
    Human save(Human human);

    Optional<Human> findById(Integer id);

    List<Human> findAll();

    List<Human> findAllByUserId(Integer id);


    void deleteById(Integer id);

    void delete(Human entity);

    void updateFate(Integer id, String fate);

    List<Human> findAllById(Iterable<Integer> ids);
}
