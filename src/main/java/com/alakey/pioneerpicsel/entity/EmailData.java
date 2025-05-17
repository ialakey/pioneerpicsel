package com.alakey.pioneerpicsel.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "email_data", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class EmailData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @ManyToOne
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;
}
