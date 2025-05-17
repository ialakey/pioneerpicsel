package com.alakey.pioneerpicsel.service.impl;

import com.alakey.pioneerpicsel.entity.*;
import com.alakey.pioneerpicsel.repository.*;
import com.alakey.pioneerpicsel.service.UserDataService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserDataServiceImpl implements UserDataService {

    private final UserRepository userRepository;
    private final EmailDataRepository emailRepo;
    private final PhoneDataRepository phoneRepo;

    @Override
    @Transactional
    @CacheEvict(value = "users", key = "#userId")
    public void addEmail(Long userId, String email) {
        if (emailRepo.existsByEmail(email)) throw new RuntimeException("Email already in use");

        UserInfo userInfo = userRepository.findById(userId).orElseThrow();
        EmailData emailData = new EmailData();
        emailData.setEmail(email);
        emailData.setUserInfo(userInfo);

        userInfo.getEmails().add(emailData);
        userRepository.save(userInfo);
    }

    @Override
    @Transactional
    public void removeEmail(Long userId, String email) {
        EmailData emailData = emailRepo.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("Email not found"));

        if (!emailData.getUserInfo().getId().equals(userId)) {
            throw new RuntimeException("Access denied");
        }

        UserInfo userInfo = emailData.getUserInfo();
        if (userInfo.getEmails().size() <= 1) {
            throw new RuntimeException("At least one email required");
        }

        userInfo.getEmails().remove(emailData);
        emailRepo.delete(emailData);
    }

    @Override
    @Transactional
    public void addPhone(Long userId, String phone) {
        if (phoneRepo.existsByPhone(phone)) {
            throw new RuntimeException("Phone already in use");
        }

        UserInfo userInfo = userRepository.findById(userId).orElseThrow();
        PhoneData phoneData = new PhoneData();
        phoneData.setPhone(phone);
        phoneData.setUserInfo(userInfo);

        userInfo.getPhones().add(phoneData);
        userRepository.save(userInfo);
    }

    @Override
    @Transactional
    public void removePhone(Long userId, String phone) {
        PhoneData phoneData = phoneRepo.findByPhone(phone)
                .orElseThrow(() -> new NoSuchElementException("Phone not found"));

        if (!phoneData.getUserInfo().getId().equals(userId)) {
            throw new RuntimeException("Access denied");
        }

        UserInfo userInfo = phoneData.getUserInfo();
        if (userInfo.getPhones().size() <= 1) {
            throw new RuntimeException("At least one phone required");
        }

        userInfo.getPhones().remove(phoneData);
        phoneRepo.delete(phoneData);
    }
}
