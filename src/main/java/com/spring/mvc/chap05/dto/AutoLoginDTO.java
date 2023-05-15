package com.spring.mvc.chap05.dto;

import lombok.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class AutoLoginDTO {

    private String sessionId;
    private LocalDateTime limitTime;
    private String account;
}
