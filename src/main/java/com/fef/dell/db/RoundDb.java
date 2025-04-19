package com.fef.dell.db;

import com.fef.dell.domain.entity.BattleEntity;
import com.fef.dell.domain.entity.RoundEntity;
import com.fef.dell.domain.repository.RoundRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class RoundDb implements RoundRepository {
    private final Map<Integer, RoundEntity> db = new HashMap<>();

    @Override
    public void add(int id, RoundEntity round) {
        db.put(id,round);
    }

    @Override
    public Optional<RoundEntity> findById(int id) {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public List<RoundEntity> getAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public Map<Integer, RoundEntity> getMap() {
        return db;
    }
}
