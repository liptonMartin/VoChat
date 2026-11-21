package com.lipton.auth.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false, unique = true, length = 255)
    private String username;

    @Column(nullable = false)
    @Getter
    @JsonIgnore
    private String password;

    public UserEntity(String username, String hashPassword) {
        this.username = username;
        this.password = hashPassword;
    }

    protected UserEntity() {
    }
}
