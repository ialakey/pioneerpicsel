package com.alakey.pioneerpicsel.repository;

import com.alakey.pioneerpicsel.entity.PhoneData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhoneDataRepository extends JpaRepository<PhoneData, Long> {
    Optional<PhoneData> findByPhone(String phone);
    boolean existsByPhone(String phone);
}
