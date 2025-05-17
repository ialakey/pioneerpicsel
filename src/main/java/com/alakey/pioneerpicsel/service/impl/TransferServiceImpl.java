package com.alakey.pioneerpicsel.service.impl;

import com.alakey.pioneerpicsel.entity.Account;
import com.alakey.pioneerpicsel.repository.AccountRepository;
import com.alakey.pioneerpicsel.service.TransferService;
import jakarta.persistence.LockModeType;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final AccountRepository accountRepository;

    private final EntityManager entityManager;

    @Override
    @Transactional
    public void transfer(Long fromUserId, Long toUserId, BigDecimal amount) {
        if (fromUserId.equals(toUserId)) {
            throw new IllegalArgumentException("Cannot transfer to self.");
        }

        Account from = entityManager
                .createQuery("SELECT a FROM Account a WHERE a.userInfo.id = :uid", Account.class)
                .setParameter("uid", fromUserId)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .getSingleResult();

        Account to = entityManager
                .createQuery("SELECT a FROM Account a WHERE a.userInfo.id = :uid", Account.class)
                .setParameter("uid", toUserId)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .getSingleResult();

        if (from.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));

        accountRepository.save(from);
        accountRepository.save(to);
    }
}
