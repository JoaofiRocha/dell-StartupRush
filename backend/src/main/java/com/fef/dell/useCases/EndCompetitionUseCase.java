package com.fef.dell.useCases;

import com.fef.dell.domain.entity.BattleEntity;
import com.fef.dell.domain.entity.CompetitionEntity;
import com.fef.dell.domain.entity.StartupEntity;

public class EndCompetitionUseCase {

    private final CompetitionEntity competition;

    public EndCompetitionUseCase(CompetitionEntity competition) {
        this.competition = competition;
    }

    public void execute() {
        BattleEntity finalBattle = competition.getCurrentRound().getBattles().getFirst();
        StartupEntity winner = finalBattle.getWinner();

        competition.setCompetitionStateEnd();
        competition.setWinner(winner);
    }
}
