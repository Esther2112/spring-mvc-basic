package com.spring.mvc.chap05.entity;
/*
create table tbl_reply (
        reply_no int(10) auto_increment,
        reply_text varchar(1000) not null,
        reply_writer varchar(100) not null,
        reply_date datetime default current_timestamp,
        board_no int(10),
        constraint pk_reply primary key (reply_no),
        constraint fk_reply foreign key (board_no) references tbl_board (board_no)
        on delete cascade
        );
 */

import lombok.*;

import java.time.LocalDateTime;

@Setter @Getter @ToString
@NoArgsConstructor @AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Reply {

    private long replyNo;
    private String replyText;
    private String replyWriter;
    private LocalDateTime replyDate;
    private long boardNo;


}
