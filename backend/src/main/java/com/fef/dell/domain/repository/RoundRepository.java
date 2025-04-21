package com.fef.dell.domain.repository;

import com.fef.dell.domain.entity.BattleEntity;
import com.fef.dell.domain.entity.RoundEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface RoundRepository {
    void add(int id, RoundEntity round);
    Optional<RoundEntity> findById(int id);
    List<RoundEntity> getAll();
    Map<Integer,RoundEntity> getMap();
}
