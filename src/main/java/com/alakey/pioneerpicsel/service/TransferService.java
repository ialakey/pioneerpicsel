package com.alakey.pioneerpicsel.service;

import java.math.BigDecimal;

public interface TransferService {
    void transfer(Long fromUserId, Long toUserId, BigDecimal amount);
}
