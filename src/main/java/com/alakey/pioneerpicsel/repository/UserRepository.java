package com.alakey.pioneerpicsel.repository;

import com.alakey.pioneerpicsel.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, Long>, JpaSpecificationExecutor<UserInfo> {
    Optional<UserInfo> findByEmails_Email(String email);
    Optional<UserInfo> findByPhones_Phone(String phone);
}