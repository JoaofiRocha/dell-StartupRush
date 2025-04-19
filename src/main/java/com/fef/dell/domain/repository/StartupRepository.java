package com.fef.dell.domain.repository;

import com.fef.dell.domain.entity.StartupEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface StartupRepository {
    void add(int id, StartupEntity startup);
    void remove(int id);
    Optional<StartupEntity> findById(int id);
    List<StartupEntity> getAll();
    Map<Integer, StartupEntity> getMap();
    int size();
}
