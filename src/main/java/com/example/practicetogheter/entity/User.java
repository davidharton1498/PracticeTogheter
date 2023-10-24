package com.example.practicetogheter.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;
import java.util.Set;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    private String password;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private int enabled;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String phoneNumber;
    private String gender;
    private String dateOfBirth;

    @OneToMany
    private List<Ride> rides;



    //Driver
    private String carBrand;
    private String carColor;
    private int passengerCapacity;


    @PreRemove
    public void deleteRoles() {
        this.getRoles().clear();
    }

}


