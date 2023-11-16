package com.academy.spring_lv4.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    private char gender='M';
    @Column(name = "phone_number")
    private String phoneNumber;
    private String address;
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum auth;

    public User(String email, String password, char gender, String phoneNumber, String address, UserRoleEnum auth) {
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.auth = auth;
    }
}
