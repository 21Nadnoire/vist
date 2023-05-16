package com.anzuza.anzuza.repository;

import com.anzuza.anzuza.model.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor,Integer> {
}
