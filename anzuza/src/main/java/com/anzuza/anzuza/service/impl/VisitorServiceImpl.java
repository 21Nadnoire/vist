package com.anzuza.anzuza.service.impl;

import com.anzuza.anzuza.model.User;
import com.anzuza.anzuza.model.Visitor;
import com.anzuza.anzuza.repository.UserRepo;
import com.anzuza.anzuza.repository.VisitorRepository;
import com.anzuza.anzuza.service.VisitorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    VisitorRepository visitorRepository;

    @Autowired
    UserRepo userRepo;

    @Override
    public Visitor createVisitor(Visitor visitor) {
        return visitorRepository.save(visitor);
    }


    @Override
    public void deleteVisitor(int id) {
        visitorRepository.deleteById(id);
    }

    @Override
    public List<Visitor> getAll(Long userId) {
        Optional<User> user = userRepo.findById(userId);
        return user.get().getVisitors();
    }

    @Override
    public Optional<Visitor> findVisitorById(int visitorId) {
        return visitorRepository.findById(visitorId);
    }


}
