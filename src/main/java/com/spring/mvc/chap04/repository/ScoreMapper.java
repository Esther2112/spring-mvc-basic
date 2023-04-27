package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Score;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScoreMapper {

//    List<Score> findAll();
    List<Score> findAll(String sort);

    boolean save(Score score);

    boolean deleteByStuNum(int stuNum);

    Score findByStuNum(int stuNum);

    boolean changeScore(Score score);
}
