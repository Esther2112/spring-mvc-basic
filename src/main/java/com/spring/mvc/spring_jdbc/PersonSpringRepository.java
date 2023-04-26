package com.spring.mvc.spring_jdbc;

import com.spring.mvc.jdbc.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PersonSpringRepository {

    // 스프링 JDBC 활용 - 데이터베이스 접속 설정 정보
    // 설정파일을 통해 불러와서 사용합니다
    private final JdbcTemplate jdbcTemplate;


    //저장 기능
    public void savePerson(Person p){
        String sql = "insert into person (person_name, person_age) values(?, ?)";
        jdbcTemplate.update(sql, p.getPersonName(), p.getPersonAge());
    }

    public void removePerson(long id){
        String sql = "delete from person where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public boolean modify(Person p){
        String sql = "update person set person_name = ?, person_age = ? where id = ?";
        int result = jdbcTemplate.update(sql, p.getPersonName(), p.getPersonAge(), p.getId());
        return result == 1;
    }

    //전체 조회 기능
    public List<Person> findAl(){
        String sql = "select * from person";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Person(rs));
    }

    //개별조회
    public Person findOne(long id){
        String sql = "select * from person where id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Person(rs), id);
    }
}
