package com.fef.dell.InterfaceAdapter.Routes;

import com.fef.dell.InterfaceAdapter.dto.RegisterStartupDto;
import com.fef.dell.domain.entity.BattleEntity;
import com.fef.dell.domain.entity.CompetitionEntity;
import com.fef.dell.domain.entity.RoundEntity;
import com.fef.dell.domain.entity.StartupEntity;
import com.fef.dell.domain.enums.Events;
import com.fef.dell.domain.repository.BattleRepository;
import com.fef.dell.domain.repository.RoundRepository;
import com.fef.dell.useCases.NextRoundUseCase;
import com.fef.dell.useCases.RegisterStartupUseCase;
import com.fef.dell.useCases.RunBattleUseCase;
import com.fef.dell.useCases.StartCompetitionUseCase;
import com.fef.dell.domain.repository.StartupRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Map;

@RestController
public class Routes {

    private final RegisterStartupUseCase registerStartupUseCase;
    private final StartupRepository startupRepository;
    private final StartCompetitionUseCase startCompetitionUseCase;
    private final BattleRepository battleRepository;
    private final RunBattleUseCase runBattleUseCase;
    private final NextRoundUseCase nextRoundUseCase;
    private  final RoundRepository roundRepository;
    private final CompetitionEntity competition;

    public Routes(RegisterStartupUseCase registerStartupUseCase, StartupRepository startupRepository, StartCompetitionUseCase startCompetitionUseCase, BattleRepository battleRepository, RunBattleUseCase runBattleUseCase, NextRoundUseCase nextRoundUseCase, RoundRepository roundRepository, CompetitionEntity competition) {
        this.registerStartupUseCase = registerStartupUseCase;
        this.startupRepository = startupRepository;
        this.startCompetitionUseCase = startCompetitionUseCase;
        this.battleRepository = battleRepository;
        this.runBattleUseCase = runBattleUseCase;
        this.nextRoundUseCase = nextRoundUseCase;
        this.roundRepository = roundRepository;
        this.competition = competition;
    }

    //register Startups
    @PostMapping("/registerStartups")
    public List<StartupEntity> register(@RequestBody RegisterStartupDto request) {
        return registerStartupUseCase.register(request);
    }

    //list Startups
    @GetMapping("/startups")
    public List<StartupEntity> getAllStartups() {
        return startupRepository.getAll();
    }


    //start comp
    @PostMapping("/competition/start")
    public Map<Integer, BattleEntity> startCompetition() {
        return startCompetitionUseCase.start();
    }

    @GetMapping("manageBattle")
    public List<BattleEntity> getAllBattles(){return battleRepository.getAll();}

    //get battle
    @GetMapping("/manageBattle/{battleID}")
    public BattleEntity getBattle(@PathVariable("battleID") int battleID){
        return battleRepository.findById(battleID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Battle not found"));
    }

    //get Startup in battle id
    @GetMapping("/manageBattle/{battleID}/list")
    public List<StartupEntity> getBattleStartups(@PathVariable("battleID") int battleID){
        BattleEntity battle = battleRepository.findById(battleID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Battle not found"));
        return battle.getStartups();
    }

    //add event
    @PostMapping("/manageBattle/{startupId}/setEvent/{event}")
    public StartupEntity getStartupSetEvent(@PathVariable("startupId") int startupId, @PathVariable("event") Events event){
        StartupEntity startup = startupRepository.findById(startupId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Startup not found"));

        startup.addEvent(event);
        return startup;
    }

    //set winner in battle id
    @GetMapping("/manageBattle/{battleID}/winner")
    public StartupEntity getBattleWinner(@PathVariable("battleID") int battleID){
        BattleEntity battle = battleRepository.findById(battleID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Battle not found"));
        return battle.getWinner();
    }

    //get run battle
    @PostMapping("/manageBattle/{battleID}/run")
    public StartupEntity runBattle(@PathVariable("battleID") int battleID){
       return runBattleUseCase.run(getBattle(battleID));
    }


    @PostMapping("/nextRound")
    public void nextRound(){
        nextRoundUseCase.execute();
    }

    @GetMapping("/manageRound/{roundID}")
    public RoundEntity getRound(@PathVariable("roundID") int roundID){
        return roundRepository.findById(roundID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Round not found"));
    }

    @GetMapping("/manageRound")
    public List<RoundEntity> getAllRounds(){
        return roundRepository.getAll();
    }


    @GetMapping("/getTable")
    public List<StartupEntity> getTable(){
        return competition.getTable();
    }
}
