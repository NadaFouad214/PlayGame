package com.example.demo.appuser;

import com.example.demo.games.DiceScoreGame;
import com.example.demo.games.PokemonDamageCalculatorGame;
import com.example.demo.games.RockPaperScissorsGame;
import com.example.demo.games.TowerOfHanoiGame;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class AppUserControllerTest {

    @InjectMocks
    private AppUserController appUserController;

    @Mock
    private  AppUserService appUserService;


    @Test
    void TowerGame() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        TowerOfHanoiGame towerOfHanoiGame = new TowerOfHanoiGame(3);
        appUserService.TowerOfHanoi(towerOfHanoiGame);
        ResponseEntity<Object> responseEntity = appUserController.game(towerOfHanoiGame);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/discs");
    }

    @Test
    void RSPTestGame() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        RockPaperScissorsGame rockPaperScissorsGame=new RockPaperScissorsGame();
        rockPaperScissorsGame.setPlayer1("Rock");
        rockPaperScissorsGame.setPlayer2("Paper");
        appUserService.RockPaperScissors(rockPaperScissorsGame);
        ResponseEntity<Object> responseEntity = appUserController.game(rockPaperScissorsGame);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/rsp");


    }

    @Test
    void PCDTestGame1() throws Exception {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
       PokemonDamageCalculatorGame pokemonDamageCalculatorGame=new PokemonDamageCalculatorGame();
        pokemonDamageCalculatorGame.setPlayer1("fire");
        pokemonDamageCalculatorGame.setPlayer2("water");
        pokemonDamageCalculatorGame.setDefense(100);
        pokemonDamageCalculatorGame.setAttack(100);
        appUserService.PokemonDamageCalc(pokemonDamageCalculatorGame);
        ResponseEntity<Object> responseEntity = appUserController.game(pokemonDamageCalculatorGame);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/pcd");

    }

    @Test
    void DiceScoreTestGame2() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        DiceScoreGame diceScoreGame=new DiceScoreGame();
        diceScoreGame.setArr(new int[]{4,4,4,3,3});
        appUserService.DiceScore(diceScoreGame);
        ResponseEntity<Object> responseEntity = appUserController.game(diceScoreGame);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/dic");

    }
}