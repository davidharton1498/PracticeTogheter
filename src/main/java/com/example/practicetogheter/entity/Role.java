package com.example.practicetogheter.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "role")
@Getter
@Setter
public class Role {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "role_id")
        private int id;
        @Column(name = "role")
        private String name;



}



