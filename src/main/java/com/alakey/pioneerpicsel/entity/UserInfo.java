package com.alakey.pioneerpicsel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "user_info")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate dateOfBirth;

    private String password;

    @OneToOne(mappedBy = "userInfo", cascade = CascadeType.ALL)
    private Account account;

    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL)
    private List<EmailData> emails;

    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL)
    private List<PhoneData> phones;
}
