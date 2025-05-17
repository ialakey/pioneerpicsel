package com.alakey.pioneerpicsel.specification;

import com.alakey.pioneerpicsel.entity.UserInfo;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UserSpecification {

    public static Specification<UserInfo> filterUsers(String name, String email, String phone, String dob) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(name)) {
                predicates.add(cb.like(root.get("name"), name + "%"));
            }

            if (StringUtils.hasText(dob)) {
                LocalDate parsedDate = LocalDate.parse(dob, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                predicates.add(cb.greaterThan(root.get("dateOfBirth"), parsedDate));
            }

            if (StringUtils.hasText(email)) {
                Join<Object, Object> emailJoin = root.join("emails");
                predicates.add(cb.equal(emailJoin.get("email"), email));
            }

            if (StringUtils.hasText(phone)) {
                Join<Object, Object> phoneJoin = root.join("phones");
                predicates.add(cb.equal(phoneJoin.get("phone"), phone));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
