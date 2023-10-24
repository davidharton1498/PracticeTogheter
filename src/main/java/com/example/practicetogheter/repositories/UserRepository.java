package com.example.practicetogheter.repositories;

import com.example.practicetogheter.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.practicetogheter.entity.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findOne(Long id);
}
