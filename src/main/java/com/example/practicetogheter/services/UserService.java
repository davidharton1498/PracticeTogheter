package com.example.practicetogheter.services;

import com.example.practicetogheter.entity.User;

public interface UserService {

    User findByUserName(String name);

    void saveUser(User user, String role);
}



