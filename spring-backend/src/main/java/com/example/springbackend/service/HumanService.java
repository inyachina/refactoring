package com.example.springbackend.service;

import com.example.springbackend.data.dto.HumanDTO;
import com.example.springbackend.model.Human;

import java.util.List;
import java.util.Optional;

public interface HumanService {
    Human save(HumanDTO dto, String login);

    Human save(Human human);

    Human findById(Integer id);

    List<Human> findAll();

    List<Human> findAllByUsername(String login);


    void deleteById(Integer id);

    void delete(Human entity);

    Human updateFate(Integer id, String fate);

    List<Human> findAllById(Iterable<Integer> ids);
}
