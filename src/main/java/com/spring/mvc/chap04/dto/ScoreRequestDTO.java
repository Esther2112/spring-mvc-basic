package com.spring.mvc.chap04.dto;

import lombok.*;

@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreRequestDTO {

    private int stuNum;
    private String name;
    private int kor, eng, math;
}
