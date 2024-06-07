package com.ETU.api.repositories;

import com.ETU.api.entities.LtiUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LtiUserRepository extends JpaRepository<LtiUser, Integer> {
    Optional<LtiUser> findByUsername(String username);
}
