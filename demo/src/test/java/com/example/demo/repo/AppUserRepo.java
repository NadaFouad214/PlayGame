package com.example.demo.repo;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@SpringBootTest
public class AppUserRepo {

    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    void IsAppUserExistsById()
    {
        AppUser User =new AppUser(1l,"nada","fouad","nada.fouad@gmail.com","password");
        appUserRepository.save(User);
        boolean actualResult = appUserRepository.existsById(1l);
        assertThat(actualResult).isTrue();
    }


}
