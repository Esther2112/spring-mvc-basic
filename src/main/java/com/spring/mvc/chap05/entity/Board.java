package com.spring.mvc.chap05.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter @Setter
@ToString
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Board {

    private int boardNo; //게시글 번호
    private String title; //제목
    private int viewCount; //조회수
    private String textContent; //글내용
    private LocalDateTime regDateTime; //작성일자시간

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return boardNo == board.boardNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(boardNo);
    }
}
