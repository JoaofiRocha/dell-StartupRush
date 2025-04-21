package com.fef.dell.domain.repository;

import com.fef.dell.domain.entity.BattleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface BattleRepository {
    void add(int id, BattleEntity battle);
    Optional<BattleEntity> findById(int id);
    List<BattleEntity> getAll();
    Map<Integer,BattleEntity> getMap();
}
