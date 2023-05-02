package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.jdbc.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScoreMapperTest {

    @Autowired
    private ScoreMapper scoreMapper;

//    @Test
//    void findAllTest(){
////        name, num, avg
//        String sort = "total";
//        List<Score> list = scoreMapper.findAll();
//        System.out.println("list = " + list);
//        assertEquals(4, list.size());
//    }

    @Test
    void saveTest(){

        ScoreRequestDTO dto = ScoreRequestDTO.builder()
                .name("바티스")
                .kor(93)
                .eng(100)
                .math(25)
                .build();


        boolean flag = scoreMapper.save(new Score(dto));

        assertTrue(flag);
    }

    @Test
    void deleteTest(){
        int stuNum = 4;
        boolean flag = scoreMapper.deleteByStuNum(stuNum);

        assertTrue(flag);

    }

    @Test
    void findByStuNumTest(){
        int num = 7;
        Score s = scoreMapper.findByStuNum(num);
        System.out.println("s = " + s);

    }
}