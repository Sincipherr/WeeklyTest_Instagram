package com.geekster.WeeklyTest_Instagram.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class AuthenticationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;
    private String token;
    private LocalDateTime tokenCreatedDate;

    @OneToOne
    private User user;

    public AuthenticationToken(User user){
        this.user=user;
        this.tokenCreatedDate=LocalDateTime.now();
        this.token= UUID.randomUUID().toString();
    }
}
