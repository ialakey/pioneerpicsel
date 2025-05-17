package com.alakey.pioneerpicsel.repository;

import com.alakey.pioneerpicsel.entity.EmailData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailDataRepository extends JpaRepository<EmailData, Long> {
    Optional<EmailData> findByEmail(String email);
    boolean existsByEmail(String email);
}
