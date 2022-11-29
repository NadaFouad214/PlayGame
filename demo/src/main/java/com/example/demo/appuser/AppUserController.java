package com.example.demo.appuser;

import com.example.demo.gamesDto.DiceScoreGame;
import com.example.demo.gamesDto.DiscsGame;
import com.example.demo.gamesDto.PdcGame;
import com.example.demo.gamesDto.RspGame;
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
    public void game(@RequestBody DiscsGame discsGame) throws Exception {
        appUserService.discs(discsGame);
    }

    @PostMapping(path="/rsp")
    public void game(@RequestBody RspGame rspGame) throws Exception {
        appUserService.RSP(rspGame);
    }

    @PostMapping(path="/pcd")
    public void game(@RequestBody PdcGame pdcGame) throws Exception {
        appUserService.PDC(pdcGame);
    }

    @PostMapping(path="/dic")
    public void game(@RequestBody DiceScoreGame diceScoreGame) throws Exception {
        appUserService.DiceScore(diceScoreGame);
    }

}
