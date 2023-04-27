package com.spring.mvc.mybatis;

import com.spring.mvc.jdbc.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonMapperTest {

    @Autowired
    PersonMapper mapper;

    @Test
    @DisplayName("마이바티스 매퍼로 사람 정보 저장에 성공해야 한다")
    void saveTest(){
        //given
        Person p = Person.builder()
                .personName("뽀로로")
                .personAge(25)
                .build();

        //when
        boolean flag = mapper.save(p);

        //then
        assertTrue(flag);
    }

    @Test
    @DisplayName("마이바티스 매퍼로 사람 정보 수정에 성공해야 한다")
    void modifyTest(){
        //given
        Person p = Person.builder()
                .personName("마이갓티스")
                .personAge(35)
                .id(3L)
                .build();

        //when
        boolean flag = mapper.modify(p);

        //then
        assertTrue(flag);
    }

    @Test
    @DisplayName("마이바티스 매퍼로 사람 정보 삭제에 성공해야 한다")
    void removeTest(){
        //given
        long id = 4L;

        //when
        boolean flag = mapper.remove(id);

        //then
        assertTrue(flag);
    }

    @Test
    @DisplayName("마이바티스 매퍼로 사람 정보 전체조회에 성공해야 한다")
    void findAllTest() {

        //given

        //when
        List<Person> people = mapper.findAll();

        //then
        people.forEach(System.out::println);
        assertEquals(5, people.size());
    }

    @Test
    @DisplayName("마이바티스 매퍼로 사람 정보 개별 조회에 성공해야 한다")
    void findOneTest() {

        //given

        //when
        Person p = mapper.findOne(5L);

        //then
        System.out.println("p = " + p);
        assertEquals("가가린", p.getPersonName());
    }
}