package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.LoginRequestDTO;
import com.spring.mvc.chap05.dto.SignUpRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("SignUPDTO를 전달하면 회원가입에 성공해야 한다")
    void joinTest(){

        SignUpRequestDTO dto = new SignUpRequestDTO();
        dto.setAccount("hahaha");
        dto.setPassword("hohoho");
        dto.setName("하하하");
        dto.setEmail("hho@ho.com");

        memberService.join(dto);

    }

    @Test
    @DisplayName("계정명이 abc1234인 회원의 로그인 시도 결과 검증을 상황별로 수행해야 한다.")
    void loginTest(){
        LoginRequestDTO dto = new LoginRequestDTO();
        dto.setAccount("qwe213");
        dto.setPassword("qwe123!");

        LoginResult result = memberService.authenticate(dto);

        assertEquals(LoginResult.SUCCESS, result);
    }

}