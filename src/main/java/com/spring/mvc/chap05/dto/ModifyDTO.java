package com.spring.mvc.chap05.dto;

import lombok.*;

@Getter @Setter
@ToString @EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ModifyDTO {
    private int boardNo;
    String title;
    String textContent;
}
