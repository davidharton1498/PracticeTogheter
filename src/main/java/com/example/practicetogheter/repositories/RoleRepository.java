package com.example.practicetogheter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.practicetogheter.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
