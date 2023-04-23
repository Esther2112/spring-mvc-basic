package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.ModifyDTO;
import com.spring.mvc.chap05.dto.SimpleTimeDTO;
import com.spring.mvc.chap05.dto.WriteDTO;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    //중간 처리 기능

    //전체리스트 조회 중간처리
    public List<SimpleTimeDTO> getList() {
        return boardRepository.findAll()
                .stream()
                .map(SimpleTimeDTO::new)
                .collect(Collectors.toList());
    }

    //특정 게시글 찾기
    public SimpleTimeDTO findSimpleOne(int boardNo) {
        Board target = boardRepository.findOne(boardNo);
        target.setViewCount(target.getViewCount()+1);
        return new SimpleTimeDTO(target);
    }

    //게시글 등록하기
    public boolean write(WriteDTO dto) {
        return boardRepository.save(dto);
    }

    //게시글 수정하기
    public boolean modify(ModifyDTO dto) {
        Board target = boardRepository.findOne(dto.getBoardNo());
        target.setTitle(dto.getTitle());
        target.setTextContent(dto.getTextContent());
        return true;
    }

    //게시글 삭제하기
    public boolean delete(int boardNo) {
        return boardRepository.deleteByNo(boardNo);
    }

}
