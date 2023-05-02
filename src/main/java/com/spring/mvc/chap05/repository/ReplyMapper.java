package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.dto.page.Page;
import com.spring.mvc.chap05.entity.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {

    //댓글등록
    boolean save(Reply reply);

    //댓글수정
    boolean modify(Reply reply);

    //댓글삭제
    boolean deleteOne(long replyNo);

    //댓글 개별조회
    Reply findOne(long replyNo);

    //댓글 목록조회
    List<Reply> findAll(@Param("bn") long boardNo,
                        @Param("p") Page page);

    //댓글 수 조회
    int count(long boardNo);

}
