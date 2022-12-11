package com.example.demo.appuser;

import com.example.demo.games.DiceScoreGame;
import com.example.demo.games.TowerOfHanoiGame;
import com.example.demo.games.PokemonDamageCalculatorGame;
import com.example.demo.games.RockPaperScissorsGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/games")
public class AppUserController {

private final AppUserService appUserService;
@Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping(path="/discs")
    public void game(@RequestBody TowerOfHanoiGame towerOfHanoiGame) throws Exception {
        appUserService.TowerOfHanoi(towerOfHanoiGame);
    }

    @PostMapping(path="/rsp")
    public void game(@RequestBody RockPaperScissorsGame rockPaperScissorsGame) throws Exception {
        appUserService.RockPaperScissors(rockPaperScissorsGame);
    }

    @PostMapping(path="/pcd")
    public void game(@RequestBody PokemonDamageCalculatorGame pokemonDamageCalculatorGame) throws Exception {
        appUserService.PokemonDamageCalc(pokemonDamageCalculatorGame);
    }

    @PostMapping(path="/dic")
    public void game(@RequestBody DiceScoreGame diceScoreGame) throws Exception {
        appUserService.DiceScore(diceScoreGame);
    }

}
