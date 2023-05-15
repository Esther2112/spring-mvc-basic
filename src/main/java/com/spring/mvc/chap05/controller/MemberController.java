package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.dto.LoginRequestDTO;
import com.spring.mvc.chap05.dto.SignUpRequestDTO;
import com.spring.mvc.chap05.service.LoginResult;
import com.spring.mvc.chap05.service.MemberService;
import com.spring.mvc.util.LoginUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.spring.mvc.util.LoginUtil.*;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    //회원 가입 요청
    //회원가입 양식 요청
    @GetMapping("/sign-up")
    public String signUp() {
        log.info("/members/sign-up GET - forwarding to jsp");
        return "members/sign-up";
    }

    //회원가입 처리 요청
    @PostMapping("/sign-up")
    public String signUp(SignUpRequestDTO dto) {
        log.info("/members/sign-up POST ! - {}", dto);

        boolean flag = memberService.join(dto);

        return "redirect:/board/list";
    }

    //아이디, 이메일 중복검사
    //비동기 요청 처리
    @GetMapping("/check")
    @ResponseBody
    public ResponseEntity<?> check(String type, String value) {
        log.info("/members/check?type={}&value={} ASYNC GET!", type, value);
        boolean flag = memberService.checkSignUpValue(type, value);
        return ResponseEntity.ok().body(flag);
    }

    //로그인 양식 요청
    @GetMapping("/sign-in")
    public String signIn(HttpServletRequest request) {
        log.info("/members/sign-in GET - forwarding to jsp");

        //요청정보 헤어 안에는 referer라는 키가 있는데
        //여기 값은 이 페이지로 들어올때 어디에서 왔는지에 대한
        //URI 정보가 기록되어 있음

//        String referer =
//        log.info("referer : {}", referer);

        return "/members/sign-in";
    }

    //로그인 검증 요청
    @PostMapping("/sign-in")
    public String signIn(LoginRequestDTO dto
                         //리다이렉션시 두번째 응답에 데이터를 보내기 위해
                         // Model이 아닌 RedirectAttributes 객체 사용
            , RedirectAttributes ra
            , HttpServletRequest request
            , HttpServletResponse response
    ) {
        log.info("/members/sign-in POST ! {} ", dto);

        LoginResult result = memberService.authenticate(dto, request.getSession(), response);

        //로그인 성공시
        if (result == LoginResult.SUCCESS) {

            //서버에서 세션에 로그인 정보를 저장
            memberService.maintainLoginState(request.getSession(), dto.getAccount());

//            //쿠키 만들기
//            Cookie loginCookie = new Cookie("login", "도롱뇽");
//            //쿠키 셋팅 ( 이 경로에서만 쿠키 들고 다니세요. 쿠키 유효범위 설정 )
//            loginCookie.setPath("/"); //모든 경로
//            loginCookie.setMaxAge(60 * 60 * 24); // 초단위 수명 지정. 하루 쿠키
//
//            //쿠키를 응답시 실어서 클라이언트에게 전송
//            response.addCookie(loginCookie);

            return "redirect:/";
        }

        //1회용으로 쓰고 버릴 데이터
        ra.addFlashAttribute("msg", result);
        //로그인 실패시
        return "redirect:/members/sign-in";
    }

    //로그아웃 요청 처리
    @GetMapping("/sign-out")
    public String signOut(
            HttpServletRequest request
            , HttpServletResponse response
    ) {
        HttpSession session = request.getSession();
        //로그인 중인지 확인
        if (isLogin(session)) {
            if (isAutoLogin(request)) {
                memberService.autoLoginClear(request, response);
            }
            //세션에서 로그인 정보를 제거
            session.removeAttribute("login");

            //세션을 초기화
            session.invalidate();

            return "redirect:/";
        }

        return "redirect:/members/sign-in";
    }
}
