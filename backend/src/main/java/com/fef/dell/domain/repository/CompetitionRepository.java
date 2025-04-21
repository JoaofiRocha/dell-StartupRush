package com.fef.dell.domain.repository;

import com.fef.dell.domain.entity.CompetitionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface CompetitionRepository {
    void add(int id ,CompetitionEntity competition);
    Optional<CompetitionEntity> findById(int id);
    List<CompetitionEntity> getAll();
}
