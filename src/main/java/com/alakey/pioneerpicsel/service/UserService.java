package com.alakey.pioneerpicsel.service;


import com.alakey.pioneerpicsel.entity.UserInfo;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserInfo> findById(Long id);
    List<UserInfo> search(String name, String email, String phone, String dob, int page, int size);
}
