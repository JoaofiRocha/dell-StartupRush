package com.fef.dell.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter()
public class BattleEntity {
    @Getter static int counter = 0;
    int id;

    List<StartupEntity> startups;
    @Setter StartupEntity winner;

    public BattleEntity(List<StartupEntity> startups){
        id = counter++;

        this.startups = startups;
    }

}
