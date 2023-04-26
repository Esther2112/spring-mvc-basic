package com.spring.mvc.jdbc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    PersonRepository repository;

    @Test
    @DisplayName("사람의 이름과 나이 정보를 DB person table에 잘 삽입해야 한다.")
    void saveTest(){
        //given
        Person p = new Person();
        p.setPersonName("미쉐린");
        p.setPersonAge(100);

        //when
        repository.save(p);

        //then



    }

    @Test
    @DisplayName("사람의 이름과 나이 정보를 DB person table에 잘 수정해야 한다.")
    void updateTest(){
        //given
        Person p = new Person();
        p.setPersonName("수정수정");
        p.setPersonAge(90);
        p.setId(1);

        //when
        repository.update(p);

        //then

    }
    @Test
    @DisplayName("사람의 이름과 나이 정보를 DB person table에 잘 삭제해야 한다.")
    void removeTest(){
        //given
        Person p = new Person();
        p.setPersonName("수정수정");
        p.setPersonAge(90);
        p.setId(7);

        //when
        repository.remove(p.getId());

        //then

    }

    @Test
    void findAllTest(){
        repository.findAll();
    }

    @Test
    void findOne(){
        Person one = repository.findOne(5L);
        System.out.println("one = " + one);
    }
}