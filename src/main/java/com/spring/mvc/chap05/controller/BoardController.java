package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.dto.ModifyDTO;
import com.spring.mvc.chap05.dto.SimpleTimeDTO;
import com.spring.mvc.chap05.dto.WriteDTO;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    //게시물 목록조회
    @GetMapping("/list")
    public String list(Model model) {
        List<SimpleTimeDTO> bList = boardService.getList();
        model.addAttribute("bList", bList);
        System.out.println("bList = " + bList);
        return "chap05/list";
    }

    //게시물 상세조회
    @GetMapping("/detail")
    public String detail(int boardNo, Model model){
        SimpleTimeDTO board = boardService.findSimpleOne(boardNo);
        model.addAttribute("b", board);
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
