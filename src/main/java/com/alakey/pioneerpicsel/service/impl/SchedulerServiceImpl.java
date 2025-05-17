package com.alakey.pioneerpicsel.service.impl;

import com.alakey.pioneerpicsel.entity.Account;
import com.alakey.pioneerpicsel.repository.AccountRepository;
import com.alakey.pioneerpicsel.service.SchedulerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchedulerServiceImpl implements SchedulerService {

    private final AccountRepository accountRepository;

    @Override
    @Scheduled(fixedRate = 30_000)
    @Transactional
    public void applyPeriodicInterest() {
        List<Account> accounts = accountRepository.findAll();

        for (Account account : accounts) {
            BigDecimal current = account.getBalance();
            BigDecimal initial = account.getInitialBalance();
            BigDecimal maxBalance = initial.multiply(BigDecimal.valueOf(2.07));

            if (current.compareTo(maxBalance) >= 0) {
                continue;
            }

            BigDecimal increased = current.multiply(BigDecimal.valueOf(1.1))
                                          .setScale(2, RoundingMode.HALF_UP);

            if (increased.compareTo(maxBalance) > 0) {
                increased = maxBalance;
            }

            account.setBalance(increased);
            log.info("Account {} updated: {} -> {}", account.getId(), current, increased);
        }

        accountRepository.saveAll(accounts);
    }
}
