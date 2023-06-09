package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.dto.WriteDTO;
import com.spring.mvc.chap05.entity.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardMapperTest {

    @Autowired
    BoardMapper mapper;

//    @Test
//    @DisplayName("게시물 300개를 작성해야 한다.")
//    void bulkInsertTest(){
//        //given
//        for (int i = 1; i <= 300 ; i++) {
//        WriteDTO dto = WriteDTO.builder()
//                .title("테스트제목 " + i)
//                .textContent("테스트내용 " + i)
//                .build();
//        mapper.save(dto);
//        }
//    }
    
    @Test
    void findTest(){
        Board board = mapper.findOne(306);
        System.out.println("board = " + board);
    }
}