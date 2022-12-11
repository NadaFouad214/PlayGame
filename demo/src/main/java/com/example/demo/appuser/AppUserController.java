package com.example.demo.appuser;

import com.example.demo.games.DiceScoreGame;
import com.example.demo.games.TowerOfHanoiGame;
import com.example.demo.games.PokemonDamageCalculatorGame;
import com.example.demo.games.RockPaperScissorsGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "api/v1/games")
public class AppUserController {

    private final AppUserService appUserService;
@Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }
    @PostMapping(path="/discs")
    public ResponseEntity<Object> game(@RequestBody TowerOfHanoiGame towerOfHanoiGame) throws Exception {
        appUserService.TowerOfHanoi(towerOfHanoiGame);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/discs")
                .buildAndExpand()
                .toUri();

        return ResponseEntity.created(location).build();

    }

    @PostMapping(path="/rsp")
    public ResponseEntity<Object> game(@RequestBody RockPaperScissorsGame rockPaperScissorsGame) throws Exception {
        appUserService.RockPaperScissors(rockPaperScissorsGame);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/rsp")
                .buildAndExpand()
                .toUri();
        return ResponseEntity.created(location).build();

    }

    @PostMapping(path="/pcd")
    public ResponseEntity<Object>  game(@RequestBody PokemonDamageCalculatorGame pokemonDamageCalculatorGame) throws Exception {
        appUserService.PokemonDamageCalc(pokemonDamageCalculatorGame);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/pcd")
                .buildAndExpand()
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping(path="/dic")
    public ResponseEntity<Object> game(@RequestBody DiceScoreGame diceScoreGame) throws Exception {
        appUserService.DiceScore(diceScoreGame);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/dic")
                .buildAndExpand()
                .toUri();
        return ResponseEntity.created(location).build();

    }

}
