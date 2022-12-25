package com.example.springbackend.service.impl;

import com.example.springbackend.exception.NotFoundException;
import com.example.springbackend.model.Human;
import com.example.springbackend.model.HumanOrder;
import com.example.springbackend.repository.HumanOrderRepository;
import com.example.springbackend.service.HumanOrderService;
import com.example.springbackend.service.HumanService;
import com.example.springbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HumanOrderServiceImpl implements HumanOrderService {
    private final HumanOrderRepository humanOrderRepository;
    private final HumanService humanService;
    private final UserService userService;

    @Override
    public HumanOrder save(Integer humanId, String login) {
        return this.humanOrderRepository.save(new HumanOrder(
                "IN_PROGRESS",
                this.userService.findByLogin(login).getId(),
                this.humanService.findById(humanId)));
    }

    @Override
    public Optional<HumanOrder> findById(Integer id) {
        return this.humanOrderRepository.findById(id);
    }

    @Override
    public HumanOrder acceptHumanFateOrder(Integer id, String fate) {
        return this.humanOrderRepository.findByHumanId(id).map((humanOrder)->{
            humanOrder.setStatus("ACCEPTED");
            Human human = this.humanService.findById(id);
            human.setFate(fate);
            this.humanService.save(human);
            return humanOrder;
        }).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<HumanOrder> findAll() {
        return this.humanOrderRepository.findAll();
    }

    @Override
    public List<HumanOrder> findAllByStatus(String status) {
        return this.humanOrderRepository.findAllByStatus(status);
    }

    @Override
    public void deleteById(Integer id) {
        this.humanOrderRepository.deleteById(id);
    }

    @Override
    public void delete(HumanOrder order) {
        this.humanOrderRepository.delete(order);
    }

}
