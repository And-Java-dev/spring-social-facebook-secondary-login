package com.example.springsocialfacebooksecondarylogin.persistence.repository;

import com.example.springsocialfacebooksecondarylogin.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReopository  extends JpaRepository<User, Long> {
    User findByUsername(final String username);
}
