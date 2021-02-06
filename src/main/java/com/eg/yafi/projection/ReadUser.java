package com.eg.yafi.projection;

public class ReadUser{
    public Long id;
    public String username;
    public String password;
    public String role;
    public boolean enabled;

    public ReadUser(Long id, String username, String password, String role, boolean enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }
}
