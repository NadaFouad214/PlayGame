package com.example.demo.appuser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class AppUser implements UserDetails {



    @SequenceGenerator(
            name = "gamer_sequence",
            sequenceName = "gamer_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator ="gamer_sequence")
    private Long id;
    private String firstname;
    private String lastname;

    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    private Boolean locked=false;
    private boolean enabled=false;

    private String rsp_score=null;
    private int pdc_score=0;
    private int dic_score=0;
    private int discs_score=0;



    public String getRsp_score() {
        return rsp_score;
    }

    public void setRsp_score(String rsp_score) {
        this.rsp_score = rsp_score;
    }

    public int getPdc_score() {
        return pdc_score;
    }

    public void setPdc_score(int pdc_score) {
        this.pdc_score = pdc_score;
    }

    public int getDic_score() {
        return dic_score;
    }

    public void setDic_score(int dic_score) {
        this.dic_score = dic_score;
    }

    public int getDiscs_score(int result) {
        return discs_score;
    }

    public void setDiscs_score(int discs_score) {
        this.discs_score = discs_score;
    }

    public AppUser(String firstname, String lastname, String email, String password, AppUserRole appUserRole) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority= new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
