package com.fef.dell.db;

import com.fef.dell.domain.entity.CompetitionEntity;
import com.fef.dell.domain.repository.CompetitionRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CompetitionDb implements CompetitionRepository {
    private final Map<Integer, CompetitionEntity> db = new HashMap<>();

    public void add(int id, CompetitionEntity competition) {
        db.put(id, competition);
    }

    public Optional<CompetitionEntity> findById(int id) {
        return Optional.ofNullable(db.get(id));
    }

    public List<CompetitionEntity> getAll() {
        return new ArrayList<>(db.values());
    }


}
