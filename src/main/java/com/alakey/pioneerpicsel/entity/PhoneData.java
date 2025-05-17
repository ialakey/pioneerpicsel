package com.alakey.pioneerpicsel.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "phone_data", uniqueConstraints = @UniqueConstraint(columnNames = "phone"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PhoneData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;

    @ManyToOne
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;
}
