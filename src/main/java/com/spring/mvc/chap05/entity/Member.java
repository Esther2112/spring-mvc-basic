package com.spring.mvc.chap05.entity;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Setter @Getter
@EqualsAndHashCode @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    private String account;
    private String password;
    private String name;
    private String email;
    private Auth auth;
    private LocalDateTime regDate;
    private LocalDateTime limitTime;
    private String profileImage;
}
