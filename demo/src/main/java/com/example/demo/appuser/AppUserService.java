package com.example.demo.appuser;

import com.example.demo.gamesDto.DiceScoreGame;
import com.example.demo.gamesDto.DiscsGame;
import com.example.demo.gamesDto.PdcGame;
import com.example.demo.gamesDto.RspGame;
import com.example.demo.registration.token.ConfirmationToken;
import com.example.demo.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {


    private final static String USER_NOT_FOUND_MSG ="user with email %s not found";
    @Autowired
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,email)));


    }
    public String signUpUser(AppUser appUser) {
        boolean userExists = appUserRepository
                .findByEmail(appUser.getEmail())
                .isPresent();

        if (userExists) {

            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);


        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

//        TODO: SEND EMAIL

        return token;
    }
    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }

public AppUser getAppUser() throws Exception {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentUserName="";
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
        currentUserName = authentication.getName();
    }
    Optional<AppUser> user=appUserRepository.findByEmail(currentUserName);
    if (!user.isPresent()) {
        throw new Exception("user not present");
    }
   return user.get();
}

    public void discs(DiscsGame discsGame) throws Exception {
        AppUser appUser=getAppUser();

        int result=0;
        if (discsGame.getMove()==0) {
            result= 0;
        } else {
           result= (int) (Math.pow(2,discsGame.getMove()) - 1);
        }
        appUser.setDiscs_score(result);
        appUserRepository.save(appUser);
    }


    public void RSP(RspGame rspGame) throws Exception {

        AppUser appUser=getAppUser();
        String result="";

        if(rspGame.getPlayer1().equals("Rock")&& rspGame.getPlayer2().equals("Scissors")||rspGame.getPlayer1().equals("Scissors")&&  rspGame.getPlayer2().equals("Paper")||rspGame.getPlayer1().equals("Paper")&&  rspGame.getPlayer2().equals("Rock"))
        {
            result= "winner is p1";
        } else if (rspGame.getPlayer2().equals("Rock")&&rspGame.getPlayer1().equals("Scissors")|| rspGame.getPlayer2().equals("Scissors")&& rspGame.getPlayer2().equals("Paper")|| rspGame.getPlayer2().equals("Paper")&& rspGame.getPlayer1().equals("Rock")) {
            result= "winner is p2";
        }
        else
        {
            result="It's a draw";
        }
        appUser.setRsp_score(result);
        appUserRepository.save(appUser);


    }

    public void PDC(PdcGame pdcGame) throws Exception {
        AppUser appUser=getAppUser();


        int damage=0;
        if (pdcGame.getPlayer1().equals("fire")&& pdcGame.getPlayer2().equals("water")||pdcGame.getPlayer1().equals("grass")&& pdcGame.getPlayer2().equals("fire"))
        {
            damage= (int) (50*(pdcGame.getAttack()/pdcGame.getDefense())*0.5);
        }
        else if (pdcGame.getPlayer1().equals("electric")&& pdcGame.getPlayer2().equals("fire")||pdcGame.getPlayer2().equals("electric")&& pdcGame.getPlayer1().equals("fire")||pdcGame.getPlayer1().equals("grass")&& pdcGame.getPlayer2().equals("electric"))
        {
            damage= (int) (50*(pdcGame.getAttack()/pdcGame.getDefense())*1);
        }
        else if (pdcGame.getPlayer1().equals("grass")&& pdcGame.getPlayer2().equals("water"))
        {
            damage= (int) (50*(pdcGame.getAttack()/pdcGame.getDefense())*2);
        }

        appUser.setPdc_score(damage);
        appUserRepository.save(appUser);


    }

    public void DiceScore(DiceScoreGame diceScoreGame) throws Exception {
        AppUser appUser=getAppUser();
        int score=0;
        int ones=0;
        int twos=0;
        int threes=0;
        int fours=0;
        int fives=0;
        int sixs=0;
        // arr=new int[4];
       diceScoreGame.getArr();

        for (int i = 0; i < diceScoreGame.getArr().length; i++){
            if (diceScoreGame.getArr()[i]==1)
            {
                ones ++;
            }
            if(diceScoreGame.getArr()[i]==2)
            {
                twos++;
            }
            if(diceScoreGame.getArr()[i]==3)
            {
                threes++;
            }
            if (diceScoreGame.getArr()[i]==4){
                fours++;
            }
            if(diceScoreGame.getArr()[i]==5)
            {
                fives++;
            }
            if(diceScoreGame.getArr()[i]==6)
            {
                sixs++;
            }

        }
        if(ones==3)
        {
            score+=1000;
        }
        else if(twos==3)
        {
            score+=200;
        }
        else if(threes==3)
        {
            score+=300;
        }
        else if(fours==3)
        {
            score+=400;
        }
        else if (fives==3) {
            score+=500;

        }
        else if(sixs==3)
        {
            score+=600;
        }
        if (ones== 1) {
            score += 100;
        }
        if (fives == 1) {
            score += 50;
        }
        appUser.setDic_score(score);
        appUserRepository.save(appUser);
    }
}
