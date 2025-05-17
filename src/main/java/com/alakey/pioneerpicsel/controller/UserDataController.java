package com.alakey.pioneerpicsel.controller;

import com.alakey.pioneerpicsel.dto.EmailPhoneDto;
import com.alakey.pioneerpicsel.service.UserDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-data")
@RequiredArgsConstructor
public class UserDataController {

    private final UserDataService userDataService;

    private Long getCurrentUserId(Authentication authentication) {
        return (Long) authentication.getPrincipal();
    }

    @PostMapping("/email")
    public void addEmail(@RequestBody EmailPhoneDto dto, Authentication auth) {
        userDataService.addEmail(getCurrentUserId(auth), dto.getValue());
    }

    @DeleteMapping("/email")
    public void removeEmail(@RequestBody EmailPhoneDto dto, Authentication auth) {
        userDataService.removeEmail(getCurrentUserId(auth), dto.getValue());
    }

    @PostMapping("/phone")
    public void addPhone(@RequestBody EmailPhoneDto dto, Authentication auth) {
        userDataService.addPhone(getCurrentUserId(auth), dto.getValue());
    }

    @DeleteMapping("/phone")
    public void removePhone(@RequestBody EmailPhoneDto dto, Authentication auth) {
        userDataService.removePhone(getCurrentUserId(auth), dto.getValue());
    }
}
