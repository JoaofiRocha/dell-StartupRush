package com.fef.dell.db;

import com.fef.dell.domain.entity.StartupEntity;
import com.fef.dell.domain.repository.StartupRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StartupDb implements StartupRepository {
    private final Map<Integer, StartupEntity> db = new HashMap<>();

    public void add(int id,StartupEntity startup) {
        db.put(id, startup);
    }

    public void remove(int id) {
        db.remove(id);
    }

    public Optional<StartupEntity> findById(int id) {
        return Optional.ofNullable(db.get(id));
    }

    public List<StartupEntity> getAll() {
        return new ArrayList<>(db.values());
    }

    public Map<Integer ,StartupEntity> getMap() {
        return db;
    }

    public int size() {
        return db.size();
    }
}
