package com.fef.dell.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RoundEntity {
    static int counter = 1;
    int id;

    @Setter
    List<BattleEntity> battles;

    int nextRoundId;

    public RoundEntity(List<BattleEntity> battles, int nextRound){
        id = counter++;

        this.battles = battles;
        this.nextRoundId = nextRound;
    }

    public RoundEntity(int nextRound){
        id = counter++;

        this.battles = new ArrayList<>();
        this.nextRoundId = nextRound;
    }

    public void setBattle(BattleEntity battle){
        battles.add(battle);
    }

    public void setBattle(List<BattleEntity> battles){
        this.battles.addAll(battles);
    }

    public List<StartupEntity> getWinners(){
        ArrayList<StartupEntity> list = new ArrayList<>();
        for(BattleEntity battle : battles){
            list.add(battle.getWinner());
        }

        return list;
    }
}
