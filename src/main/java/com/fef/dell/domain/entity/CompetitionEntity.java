package com.fef.dell.domain.entity;

import com.fef.dell.domain.enums.CompetitionState;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.*;

@Getter()
@Component
public class CompetitionEntity {
    private static int counter = 0;
    int id;

    int currentRound;
    CompetitionState competitionState;

    @Setter
    StartupEntity winner;

    @Setter
    Map<Integer, RoundEntity> rounds = new HashMap<>();

    @Setter
    List<StartupEntity> startups = new ArrayList<>();

    public CompetitionEntity(){
        id = counter++;
        currentRound = 0;
        competitionState = CompetitionState.NOT_STARTED;
    }

    public void nextRound(){currentRound++;}

    public void setCompetitionStateStart(){competitionState = CompetitionState.RUNNING;}

    public void setCompetitionStateEnd(){competitionState = CompetitionState.ENDED;}



    public RoundEntity getCurrentRound(){
        return rounds.get(currentRound);
    }

    public List<StartupEntity> getTable(){
        Collections.sort(startups);
        return startups.reversed();
    }
}
