package com.fef.dell.db;

import com.fef.dell.domain.entity.BattleEntity;
import com.fef.dell.domain.repository.BattleRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BattleDb implements BattleRepository {
    private final Map<Integer, BattleEntity> db = new HashMap<>();

    public void add(int id, BattleEntity battle) {
        db.put(id, battle);
    }

    public Optional<BattleEntity> findById(int id) {
        return Optional.ofNullable(db.get(id));
    }

    public List<BattleEntity> getAll() {
        return new ArrayList<>(db.values());
    }

    public Map<Integer, BattleEntity> getMap() {
        return db;
    }

}
