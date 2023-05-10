package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberMapperTest {

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    PasswordEncoder encoder;

    @Test
    @DisplayName("회원가입에 성공해야 한다.")
    void registerTest(){
        Member mbr = Member.builder()
                .account("dororong")
                .password(encoder.encode("1111"))
                .name("도로롱")
                .email("dororong@naver.com")
                .build();

        boolean flag = memberMapper.save(mbr);

        assertTrue(flag);
    }

    @Test
    @DisplayName("tuturu라는 계정명으로 회원을 조회하면 그 회원의 이름은 마유시여야 한다")
    void findMemberTest(){
        String account = "tuturu";
        Member foundMbr = memberMapper.findMember(account);

        System.out.println("foundMbr = " + foundMbr);
        assertEquals("마유시", foundMbr.getName());
    }

    @Test
    @DisplayName("계정명이 banana인 경우 결과값이 1이 나와야 한다")
    void accountDupleTest(){
        String account = "banana";
        int count = memberMapper.isDuplicated("account", account);

        assertEquals(1, count);
    }

    @Test
    @DisplayName("이메일이 tuturu@naver.com인 경우 결과값이 1이 나와야 한다")
    void accountDupleTest2(){
        String email = "tuturu@naver.com";
        int count = memberMapper.isDuplicated("email", email);

        assertEquals(1, count);
    }

    @Test
    @DisplayName("비밀번호가 암호화 되어야 한다")
    void encodingTest(){
        //인코딩 전 패스워드
        String rawPassword = "abcabc123";

        //인코딩 후 패스워드
        String encodePassword = encoder.encode(rawPassword);

        System.out.println("rawPassword = " + rawPassword);
        System.out.println("encodePassword = " + encodePassword);

    }

}