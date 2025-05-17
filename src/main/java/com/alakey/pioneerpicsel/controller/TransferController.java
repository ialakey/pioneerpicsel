package com.alakey.pioneerpicsel.controller;

import com.alakey.pioneerpicsel.dto.TransferRequest;
import com.alakey.pioneerpicsel.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfer")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    public void transfer(@RequestBody TransferRequest request, Authentication authentication) {
        Long fromUserId = (Long) authentication.getPrincipal();
        transferService.transfer(fromUserId, request.getToUserId(), request.getAmount());
    }
}
