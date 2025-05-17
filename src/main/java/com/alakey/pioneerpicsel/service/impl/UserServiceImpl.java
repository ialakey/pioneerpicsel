package com.alakey.pioneerpicsel.service.impl;

import com.alakey.pioneerpicsel.entity.UserInfo;
import com.alakey.pioneerpicsel.repository.UserRepository;
import com.alakey.pioneerpicsel.service.UserService;
import com.alakey.pioneerpicsel.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Cacheable(value = "users", key = "#id")
    public Optional<UserInfo> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<UserInfo> search(String name, String email, String phone, String dob, int page, int size) {
        Specification<UserInfo> spec = UserSpecification.filterUsers(name, email, phone, dob);
        return userRepository.findAll(spec, PageRequest.of(page, size)).getContent();
    }
}
