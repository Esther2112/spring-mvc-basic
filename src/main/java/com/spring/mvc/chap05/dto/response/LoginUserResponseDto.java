package com.spring.mvc.chap05.dto.response;

import lombok.*;

@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginUserResponseDto {

    private String account;
    private String nickname;
    private String email;
    private String auth;
    private String profile;
}
