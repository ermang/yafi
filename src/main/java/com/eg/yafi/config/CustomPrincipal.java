package com.eg.yafi.config;

import com.eg.yafi.projection.ReadUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CustomPrincipal extends User{// implements UserDetails {
    private  ReadUser user;

    public CustomPrincipal(String username, String password, boolean enabled, boolean accountNonExpired,
                       boolean credentialsNonExpired, boolean accountNonLocked,
                       Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        //this.user = readUser;
    }

    @Override
    public String getUsername() {
        return user.username;
    }

    @Override
    public String getPassword() {
        return user.password;//user.getPassword();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.role));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getUserId() {
        return user.id;
    }

    public ReadUser getReadUser() {
        return user;
    }

    public void setUser(ReadUser user) {
        this.user = user;
    }
}
