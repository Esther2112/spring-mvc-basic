package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.dto.ModifyDTO;
import com.spring.mvc.chap05.dto.WriteDTO;
import com.spring.mvc.chap05.dto.page.Page;
import com.spring.mvc.chap05.dto.page.Search;
import com.spring.mvc.chap05.entity.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    //게시물 목록 조회
//    List<Board> findAll();
    List<Board> findAll(Search page);

    //게시물 상세 조회
    Board findOne(int boardNo);

    boolean modify(ModifyDTO dto);

    //게시물 등록
    boolean save(Board board);

    //게시물 삭제
    boolean deleteByNo(int boardNo);

    void updateViewCount(Board board);

    //총 게시물 수 조회하기
    int count(Search page);
}
