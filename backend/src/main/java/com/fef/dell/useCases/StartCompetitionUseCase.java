package com.fef.dell.useCases;

import com.fef.dell.domain.entity.BattleEntity;
import com.fef.dell.domain.entity.CompetitionEntity;
import com.fef.dell.domain.entity.RoundEntity;
import com.fef.dell.domain.entity.StartupEntity;
import com.fef.dell.domain.repository.BattleRepository;
import com.fef.dell.domain.repository.RoundRepository;
import com.fef.dell.domain.repository.StartupRepository;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class StartCompetitionUseCase {
    private final CompetitionEntity competition;
    private final StartupRepository startupRepository;
    private final BattleRepository battleRepository;
    private  final RoundRepository roundRepository;

    public StartCompetitionUseCase(StartupRepository startupRepository, BattleRepository battleRepository, RoundRepository roundRepository, CompetitionEntity competitionEntity) {
        this.competition = competitionEntity;
        this.startupRepository = startupRepository;
        this.battleRepository = battleRepository;
        this.roundRepository = roundRepository;
    }

    public Map<Integer,BattleEntity> start() {
        if (!(startupRepository.size() == 4 || startupRepository.size() == 8)) {
            throw new RuntimeException("Competition cannot start. Need 4 or 8 startups");
        }

        competition.nextRound();
        competition.setCompetitionStateStart();

        List<StartupEntity> startupList = new ArrayList<>(startupRepository.getAll());
        Collections.shuffle(startupList);

        for (int i = 0; i < startupList.size(); i += 2) {
            StartupEntity s1 = startupList.get(i);
            StartupEntity s2 = startupList.get(i + 1);
            BattleEntity battle = new BattleEntity(new ArrayList<>(List.of(s1,s2)));
            battleRepository.add(battle.getId(), battle);
        }

        RoundEntity round = new RoundEntity(battleRepository.getAll(),2);
        roundRepository.add(round.getId(),round);

        if(startupList.size() == 4){
            roundRepository.add(2,new RoundEntity(-1));
        }
        else{
            roundRepository.add(2,new RoundEntity(3));
            roundRepository.add(3,new RoundEntity(-1));
        }

        competition.setRounds(roundRepository.getMap());
        competition.setStartups(startupList);
        return battleRepository.getMap();
    }
}
