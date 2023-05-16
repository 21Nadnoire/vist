package com.anzuza.anzuza.service;

import com.anzuza.anzuza.model.Visitor;

import java.util.List;
import java.util.Optional;

public interface VisitorService {
    Visitor createVisitor(Visitor visitor);
    void deleteVisitor(int id);
    List<Visitor> getAll(Long userId);
    Optional<Visitor> findVisitorById(int visitorId);
}

