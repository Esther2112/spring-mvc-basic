package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.dto.WriteDTO;
import com.spring.mvc.chap05.dto.page.Search;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.entity.Reply;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyMapperTest {

    @Autowired
    BoardMapper boardMapper;
    @Autowired
    ReplyMapper replyMapper;


    @Test
    @DisplayName("게시물 300개 등록하고 1000개의 댓글 등록")
    void bulkInsertTest(){
        for (int i = 1; i <=300 ; i++) {
            WriteDTO dto = WriteDTO.builder()
                    .title("재밌는 게시물 " + i)
                    .textContent("노잼 게시물 내용 " + i)
                    .build();
            boardMapper.save(dto);
        }
        assertEquals(300, boardMapper.count(new Search()));

        for (int i = 1; i <= 300 ; i++) {
            Reply r = Reply.builder()
                    .replyWriter("잼민이 " + i)
                    .replyText("개똥아 ~ " + i)
                    .boardNo((long) (Math.random() * 300 + 1))
                    .build();
            replyMapper.save(r);
        }
    }

    @Test
    @DisplayName("3번 게시물에 댓글을 등록하면 총 댓글 수는 4개여야 한다")
    @Transactional
    @Rollback //테스트 끝난 이후 롤백해라
    void saveTest(){
        //given
        long boardNo = 3L;
        Reply r = Reply.builder()
                .replyText("쓰리랑")
                .replyWriter("삼삼이형")
                .boardNo(boardNo)
                .build();
        //when
        boolean flag = replyMapper.save(r);
        //then
        assertTrue(flag);
        assertEquals(4, replyMapper.count(boardNo));

    }

    @Test
    @DisplayName("댓글 번호가 302인 댓글을 삭제하면 3번 게시물의 총 댓글 수는 2어야 한다")
    @Transactional @Rollback
    void removeTest(){
        long replyNo = 302;
        long boardNo = 3L;
        boolean flag = replyMapper.deleteOne(replyNo);

        assertTrue(flag);
        assertEquals(2, replyMapper.count(boardNo));
    }

//    @Test
//    void bulkReplyInsert(){
//        for (int i = 0; i < 300; i++) {
//            Reply reply = Reply.builder()
//                    .boardNo(298L)
//                    .replyText("댓글내용용 " + i)
//                    .replyWriter("뚯뚜루")
//                    .build();
//            replyMapper.save(reply);
//        }
//    }

}