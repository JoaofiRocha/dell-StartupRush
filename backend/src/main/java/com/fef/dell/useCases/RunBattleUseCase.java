package com.fef.dell.useCases;

import com.fef.dell.domain.entity.BattleEntity;
import com.fef.dell.domain.entity.StartupEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class RunBattleUseCase {

    public StartupEntity run(BattleEntity battle){
        List<StartupEntity> list = battle.getStartups();
        StartupEntity winner;

        for (StartupEntity startup : list){
            startup.applyEvents();
        }

        if(list.getFirst().getPoints() == list.getLast().getPoints()){
            Collections.shuffle(list);
            winner= list.getFirst();
            winner.addPoints(2);
            battle.setWinner(winner);
        } else if (list.getFirst().getPoints() > list.getLast().getPoints()){
            winner = list.getFirst();
        }
        else{
            winner = list.getLast();
        }
        winner.addPoints(30);
        winner.resetEvents();
        battle.setWinner(winner);
        return winner;
    }
}
