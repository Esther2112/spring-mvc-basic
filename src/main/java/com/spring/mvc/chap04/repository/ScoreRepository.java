package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Score;

import java.util.List;

//역할 명세 (추상화):
//성적 정보를 잘 저장하고 추가하고 삭제하고 수정해야 한다.
//그래서 어디에 저장? 어디에서 조회? 어디에서 삭제? 몰?루
public interface ScoreRepository {

    //성적 정보 전체 목록 조회
    List<Score> findAll();
    //정렬조회
    default List<Score> findAll(String sort) {
     return null;
    } //선택적 오버라이딩 가능

    //성적 등록 처리
    boolean save(Score score);

    //성적정보 삭제
    boolean deleteByStuNum(int stuNum);

    //성적 정보 개별 조회
    Score findByStuNum(int stuNum);
}
