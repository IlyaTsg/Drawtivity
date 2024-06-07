package com.ETU.api.repositories;

import com.ETU.api.entities.LtiUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Ilya Tsygankov
 * @created 12.10.2023
 */
public interface LtiUserRepository extends JpaRepository<LtiUser, Integer> {
    Optional<LtiUser> findByUsername(String username);
}
