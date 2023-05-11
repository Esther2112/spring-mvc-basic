package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.dto.ModifyDTO;
import com.spring.mvc.chap05.dto.SimpleTimeDTO;
import com.spring.mvc.chap05.dto.WriteDTO;
import com.spring.mvc.chap05.dto.page.Page;
import com.spring.mvc.chap05.dto.page.PageMaker;
import com.spring.mvc.chap05.dto.page.Search;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {

    private final BoardService boardService;

    //게시물 목록조회
    @GetMapping("/list")
    public String list(
            Search page, Model model
            , HttpServletRequest request
    ) {

        boolean flag = false;

        //세션을 확인
        Object login = request.getSession().getAttribute("login");

        if(login != null) {
            flag = true;
        }

//        //쿠키를 확인
//        Cookie[] cookies = request.getCookies();
//        for (Cookie c : cookies) {
//            if(c.getName().equals("login")){
//                flag = true;
//                break;
//            }
//        }
        if(!flag) return "redirect:/members/sign-in";

        log.info("/board/list : GET");
        log.info("page : {}", page);
        List<SimpleTimeDTO> bList = boardService.getList(page);

        //페이징 알고리즘 작동
        PageMaker maker = new PageMaker(page, boardService.getCount(page));
        model.addAttribute("bList", bList);
        model.addAttribute("maker", maker);
        model.addAttribute("s", page);
        return "chap05/list";
    }

    //게시물 상세조회
    @GetMapping("/detail")
    public String detail(int boardNo, @ModelAttribute("s") Search search, Model model){
        SimpleTimeDTO board = boardService.findSimpleOne(boardNo);
        model.addAttribute("b", board);
//        model.addAttribute("s", search);
        return "chap05/detail";
    }

    //게시물 작성
    @GetMapping("/write")
    public String write(){
        return "chap05/write";
    }

    //게시물 등록
    @PostMapping("/regist")
    public String regist(WriteDTO dto){
        boardService.write(dto);
        return "redirect:/board/list";
    }

    //게시물 수정작성
    @GetMapping("/modify")
    public String modify(int boardNo, Model model){
        SimpleTimeDTO target = boardService.findSimpleOne(boardNo);
        model.addAttribute("b", target);
        return "chap05/modify";
    }

    //게시물 수정 등록
    @GetMapping("/modified")
    public String modified(ModifyDTO dto){
        boardService.modify(dto);
        return "redirect:/board/list";
    }

    //게시물 삭제하기 - 모달?_?
    @GetMapping("/delete")
    public String delete(int boardNo){
        boardService.delete(boardNo);
        System.out.println("delete " + boardNo);
        return "redirect:/board/list";
    }
}
