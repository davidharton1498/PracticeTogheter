package com.example.practicetogheter.repositories;

import com.example.practicetogheter.entity.User;

public interface DriverRepository {

    User findByUsername(String username);
}
