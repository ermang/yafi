package com.eg.yafi.config;

import com.eg.yafi.projection.ReadUser;
import com.eg.yafi.repo.AppUserRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AppUserRepo appUserRepo;

    public CustomUserDetailsService(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        ReadUser readUser = appUserRepo.findOneByUsernameRO(s);
        if (readUser == null)
            throw new UsernameNotFoundException(s);

        CustomPrincipal customPrincipal = new CustomPrincipal(readUser.username, readUser.password, readUser.enabled,
                true, true, true,
                Collections.singletonList(new SimpleGrantedAuthority(readUser.role)));

        customPrincipal.setUser(readUser);

        return customPrincipal;
    }
}
