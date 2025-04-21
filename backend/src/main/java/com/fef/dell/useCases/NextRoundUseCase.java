package com.fef.dell.useCases;

import com.fef.dell.domain.entity.BattleEntity;
import com.fef.dell.domain.entity.CompetitionEntity;
import com.fef.dell.domain.entity.RoundEntity;
import com.fef.dell.domain.entity.StartupEntity;
import com.fef.dell.domain.repository.BattleRepository;
import com.fef.dell.domain.repository.CompetitionRepository;
import com.fef.dell.domain.repository.RoundRepository;
import com.fef.dell.domain.repository.StartupRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class NextRoundUseCase {
    RoundRepository roundRepository;
    StartupRepository startupRepository;
    CompetitionEntity competitionEntity;
    BattleRepository battleRepository;

    public NextRoundUseCase(RoundRepository roundRepository, StartupRepository startupRepository, CompetitionRepository competitionRepository, BattleRepository battleRepository, CompetitionEntity competitionEntity){
        this.roundRepository = roundRepository;
        this.startupRepository = startupRepository;
        this.competitionEntity = competitionEntity;
        this.battleRepository = battleRepository;
    }

    public int execute(){
       RoundEntity round = competitionEntity.getCurrentRound();
        int nextRoundId = round.getNextRoundId();

       if(nextRoundId == -1){
           competitionEntity.setWinner(round.getBattles().getFirst().getWinner());
           return -1;
       }

       ArrayList<StartupEntity> winners = new ArrayList<>();
       List<BattleEntity> list = round.getBattles();
        for(BattleEntity b : list){
            if(b.getWinner() == null){
                throw new RuntimeException("Not every Battle has a Winner");
            }
            winners.add(b.getWinner());
        }

        List<BattleEntity> newBattles = new ArrayList<>();
        for (int i = 0; i < winners.size(); i+=2){
            BattleEntity battle = new BattleEntity(
                    new ArrayList<>(List.of(
                            winners.get(i), winners.get(i+1))
                    ));

            battleRepository.add(battle.getId(),battle);
            newBattles.add(battle);
        }


        round = roundRepository.findById(nextRoundId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Round not found"));

        competitionEntity.setCurrentRound(nextRoundId);
        round.setBattle(newBattles);
        return nextRoundId;
    }

}
