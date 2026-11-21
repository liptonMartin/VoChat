package com.lipton.auth.repository;

import com.lipton.auth.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Modifying
    @Query(value = """
                    INSERT INTO users (username, password)
                    VALUES (:username, :password)
                    ON CONFLICT DO NOTHING;
            """, nativeQuery = true)
    int insertUniqueUser(@Param("username") String username, @Param("password") String password);

    Optional<UserEntity> findByUsername(String username);
}
