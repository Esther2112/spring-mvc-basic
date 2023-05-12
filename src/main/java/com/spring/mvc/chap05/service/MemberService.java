package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.LoginRequestDTO;
import com.spring.mvc.chap05.dto.SignUpRequestDTO;
import com.spring.mvc.chap05.dto.response.LoginUserResponseDto;
import com.spring.mvc.chap05.entity.Member;
import com.spring.mvc.chap05.repository.MemberMapper;
import com.spring.mvc.util.LoginUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.beans.Encoder;

import static com.spring.mvc.chap05.service.LoginResult.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder encoder;

    //회원가입 처리 서비스
    public boolean join(SignUpRequestDTO dto){
        //dto를 entity로 변환
        Member member = Member.builder()
                .account(dto.getAccount())
                .email(dto.getEmail())
                .name(dto.getName())
                .password(encoder.encode(dto.getPassword()))
                .build();

        //매퍼에게 회원 정보 전달해서 저장명령
        return memberMapper.save(member);
    }

    // 중복검사 서비스 처리
    public boolean checkSignUpValue(String type, String value){
        int flagNum = memberMapper.isDuplicated(type, value);
        return flagNum == 1;
    }

    public LoginResult authenticate(LoginRequestDTO dto) {
        Member member = memberMapper.findMember(dto.getAccount());

        //회원가입 진위여부 확인
        if(member == null) {
            log.info("{} - 회원가입 안했음 ㅋ", dto.getAccount());
            return NO_ACC;
        }

        //비밀번호 일치 확인
        if(!encoder.matches(dto.getPassword(), member.getPassword())){
            log.info("비밀번호 불일치 !");
            return NO_PW;
        }

        log.info("{}님 로그인 성공!", member.getName());
        return SUCCESS;
    }

    public void maintainLoginState(HttpSession session, String account) {
        //로그인이 성공하면 세션에 로그인한 회원의 정보들을 저장
        /*
            로그인시 클라이언트에게 전달할 회원정보
            - 닉네임
            - 프로필 사진
            - 마지막 로그인 시간
         */

        //현재 로그인한 사람의 모든 정보
        Member member = getMember(account);

        //화면에 보여줄 일부 정보
        LoginUserResponseDto dto = LoginUserResponseDto.builder()
                .account(member.getAccount())
                .email(member.getEmail())
                .nickname(member.getName())
                .build();

        //dto정보를 세선에 저장
        session.setAttribute(LoginUtil.LOGIN_KEY, dto);

        //세션의 수명을 초단위 설정 - 설정 안하면 기본값 30분
        session.setMaxInactiveInterval(60 * 60); //1시간
    }

    //멤버 정보를 가져오는 서비스 기능
    public Member getMember(String account){
        return memberMapper.findMember(account);
    }
}
