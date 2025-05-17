package com.alakey.pioneerpicsel.repository;

import com.alakey.pioneerpicsel.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
