package com.example.practicetogheter.web;

import com.example.practicetogheter.repositories.UserRepository;

public class GoodbyController {

    private final UserRepository userRepository;

    public GoodbyController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}


