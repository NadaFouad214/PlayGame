package com.example.demo.appuser;

import com.example.demo.games.DiceScoreGame;
import com.example.demo.games.PokemonDamageCalculatorGame;
import com.example.demo.games.RockPaperScissorsGame;
import com.example.demo.games.TowerOfHanoiGame;
import com.example.demo.registration.token.ConfirmationTokenService;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Collection;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
class AppUserServiceTest {

    @InjectMocks
    private  AppUserService appUserService;

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    private ConfirmationTokenService confirmationTokenService;



    @Test
    void signUpUser() {
        //given
        AppUser appUser=new AppUser();
        appUser.setFirstname("nada");
        appUser.setLastname("fouad");
        appUser.setEmail("nada.fouad@gmail.com");
        appUser.setPassword("password");
        //when
        when(bCryptPasswordEncoder.encode(appUser.getPassword())).thenReturn("token");
//        when(appUserRepository.save(appUser)).thenReturn(appUser);
      //  when(appUserRepository.findByEmail("Nada.fouad@gmail")).thenReturn(Optional.of(appUser));
        appUserService.signUpUser(appUser);
        ArgumentCaptor<AppUser> appUserArgumentCaptor =ArgumentCaptor.forClass(AppUser.class);
        verify(appUserRepository).save(appUserArgumentCaptor.capture());
        AppUser capturedAppUser=appUserArgumentCaptor.getValue();
        assertThat(capturedAppUser).isEqualTo(appUser);

    }









    @Test
    void getAppUser() throws Exception {
        Authentication authentication = new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return "Nada";
            }
        };
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AppUser appUser = new AppUser();

        when(appUserRepository.findByEmail("Nada")).thenReturn(Optional.of(appUser));
        AppUser appUser1= appUserService.getAppUser();
       assertThat(appUser1.getEmail()).isEqualTo(appUser.getEmail());
       // assertTrue(authentication.getName().equals(appUser.getUsername()));
    }

    @Test
    void towerOfHanoiTest() throws Exception {
       // AppUser appUser=new AppUser(1l,"nada","fouad","nada.fouad@gmail.com","password");
        Authentication authentication = new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return "Nada";
            }
        };
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AppUser appUser = new AppUser();
        TowerOfHanoiGame towerOfHanoiGame = new TowerOfHanoiGame();
        towerOfHanoiGame.setMove(3);
        when(appUserRepository.save(appUser)).thenReturn(appUser);
        when(appUserRepository.findByEmail("Nada")).thenReturn(Optional.of(appUser));
        int result=appUserService.TowerOfHanoi(towerOfHanoiGame);
        assertEquals(result,7);
    }

    @Test
    void rockPaperScissors() throws Exception {
        Authentication authentication = new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return "Nada";
            }
        };
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AppUser appUser = new AppUser();
        RockPaperScissorsGame rockPaperScissorsGame=new RockPaperScissorsGame();
        rockPaperScissorsGame.setPlayer1("Rock");
        rockPaperScissorsGame.setPlayer2("Scissors");
        when(appUserRepository.save(appUser)).thenReturn(appUser);
        when(appUserRepository.findByEmail("Nada")).thenReturn(Optional.of(appUser));
        String result=appUserService.RockPaperScissors(rockPaperScissorsGame);
        assertTrue(result.equals("winner is p1"));



    }

    @Test
    void pokemonDamageCalc() throws Exception {
        Authentication authentication = new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return "Nada";
            }
        };
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AppUser appUser = new AppUser();
        PokemonDamageCalculatorGame pokemonDamageCalculatorGame=new PokemonDamageCalculatorGame();
        pokemonDamageCalculatorGame.setPlayer1("fire");
        pokemonDamageCalculatorGame.setPlayer2("water");
        pokemonDamageCalculatorGame.setAttack(100);
        pokemonDamageCalculatorGame.setDefense(100);
        when(appUserRepository.save(appUser)).thenReturn(appUser);
        when(appUserRepository.findByEmail("Nada")).thenReturn(Optional.of(appUser));
        int result =appUserService.PokemonDamageCalc(pokemonDamageCalculatorGame);
        assertEquals(result,25);




    }

    @Test
    void diceScore() throws Exception {
        Authentication authentication = new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return "Nada";
            }
        };
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AppUser appUser = new AppUser();
        DiceScoreGame diceScoreGame=new DiceScoreGame();
        diceScoreGame.setArr(new int[]{4,4,4,3,3});
        when(appUserRepository.save(appUser)).thenReturn(appUser);
        when(appUserRepository.findByEmail("Nada")).thenReturn(Optional.of(appUser));
        int result=appUserService.DiceScore(diceScoreGame);
        assertEquals(result,400);

    }
}