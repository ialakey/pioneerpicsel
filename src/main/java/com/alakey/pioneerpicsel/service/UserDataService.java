package com.alakey.pioneerpicsel.service;

public interface UserDataService {
    void addEmail(Long userId, String email);
    void removeEmail(Long userId, String email);
    void addPhone(Long userId, String phone);
    void removePhone(Long userId, String phone);
}
