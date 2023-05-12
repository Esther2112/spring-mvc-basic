package com.spring.mvc.util;

import com.spring.mvc.chap05.dto.LoginRequestDTO;
import com.spring.mvc.chap05.dto.response.LoginUserResponseDto;

import javax.servlet.http.HttpSession;

//회원 인증 인가 관련 상수와 메서드를 가진 객체
public class LoginUtil {

    //로그인 세션 키
    public static final String LOGIN_KEY = "login";

    //로그인 여부 확인
    public static boolean isLogin(HttpSession session){
        return session.getAttribute(LOGIN_KEY) != null;
    }

    //로그인한 사람의 계정명을 반환하는 메서드
    public static String getCurrentLoginAccount(HttpSession session){
        LoginUserResponseDto dto = (LoginUserResponseDto) session.getAttribute(LOGIN_KEY);
        return dto.getAccount();
    }
}
