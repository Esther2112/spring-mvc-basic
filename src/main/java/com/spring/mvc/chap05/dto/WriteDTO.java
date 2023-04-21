package com.spring.mvc.chap05.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@EqualsAndHashCode @ToString
@NoArgsConstructor
@AllArgsConstructor
public class WriteDTO {
    private String title;
    private String textContent;
}
