package com.spring.mvc.chap05.dto;

import com.spring.mvc.chap05.entity.Board;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Getter @Setter
@RequiredArgsConstructor
@ToString @EqualsAndHashCode
public class SimpleTimeDTO {
    private final int boardNo; //게시글 번호
    private String title; //제목
    private int viewCount; //조회수
    private String simpleTime; //작성일자시간
    private String textContent;
    private String account;
    private String writer;

    public SimpleTimeDTO(Board board) {
        this.boardNo = board.getBoardNo();
        this.title = getShortTitle(board);
        this.viewCount = board.getViewCount();
        this.textContent = board.getTextContent();
        this.simpleTime = getSimpleTime(board);
        this.account = board.getAccount();
        this.writer = board.getWriter();
    }

    public String getShortTitle(Board board){
        if(board.getTitle().length() > 5){
            return board.getTitle().substring(0, 5) + "...";
        }
        return board.getTitle();
    }

    public String getSimpleTime(Board board){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm");
        return board.getRegDateTime().format(dtf);
    }
}
