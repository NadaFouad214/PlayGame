package com.example.demo.appuser;

import com.example.demo.games.TowerOfHanoiGame;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class AppUserControllerTest {

    @Autowired
    private MockMvc mvc;
    @InjectMocks
    private AppUserController appUserController;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(appUserController).build();
    }
    @Test
    void game() throws Exception {

//        RequestBuilder builder = MockMvcRequestBuilders.post("/discs");
//        MvcResult result=mvc.perform(builder).andReturn();
//        TowerOfHanoiGame towerOfHanoiGame = new TowerOfHanoiGame();
//        towerOfHanoiGame.setMove(3);
//        assertEquals(towerOfHanoiGame,result.getResponse().getContentAsString());
//
//       mvc.perform(MockMvcRequestBuilders
//               .post("/discs").contentType(MediaType.APPLICATION_JSON))
//               .andExpect(status().isOk())
//               .andExpect(MockMvcResultMatchers.jsonPath()







    }

    @Test
    void testGame() {
    }

    @Test
    void testGame1() {
    }

    @Test
    void testGame2() {
    }
}