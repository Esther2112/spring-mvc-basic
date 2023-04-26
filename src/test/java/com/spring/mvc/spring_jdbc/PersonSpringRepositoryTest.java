package com.spring.mvc.spring_jdbc;

import com.spring.mvc.jdbc.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonSpringRepositoryTest {

    @Autowired
    PersonSpringRepository repository;

    @Test
    void savePersonTest(){
        //given
        Person p = new Person();
        p.setPersonName("김스프링");
        p.setPersonAge(11);
        //when
        repository.savePerson(p);
    }

    @Test
    void removePersonTest(){
        //given
        long id = 8L;
        repository.removePerson(id);
    }

    @Test
    void modifyPerson(){
        Person p = new Person();
        p.setId(6);
        p.setPersonName("가가멜");
        p.setPersonAge(500);
        //when
        boolean flag = repository.modify(p);
        //result
        assertTrue(flag);
    }

    @Test
    void findAllTest(){
        List<Person> people = repository.findAl();
        for (Person person : people) {
            System.out.println("person = " + person);
        }
    }
    
    @Test
    void findOne(){
        Person p = repository.findOne(2L);
        System.out.println("p = " + p);
    }
}