package com.lipton.auth.entities;

import jakarta.persistence.*;
import org.hibernate.exception.ConstraintViolationException;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Size(max = 255, message = "Username must not exceed 255 characters")
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    public UserEntity(String username, String hashPassword) {
        this.username = username;
        this.password = hashPassword;
    }

    protected UserEntity() {
    }
}
