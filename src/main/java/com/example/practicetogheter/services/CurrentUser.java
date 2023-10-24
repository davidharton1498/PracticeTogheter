package com.example.practicetogheter.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CurrentUser extends User {


    private final com.example.practicetogheter.entity.User user;

    public CurrentUser(String username, String password, Collection<?
            extends GrantedAuthority> authorities,
                       com.example.practicetogheter.entity.User user) {
        super(username, password, authorities);
        this.user = user;
    }

    public com.example.practicetogheter.entity.User getUser() {
        return user;
    }
}


