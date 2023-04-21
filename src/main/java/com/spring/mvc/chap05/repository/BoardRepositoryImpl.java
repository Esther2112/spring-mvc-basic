package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.dto.WriteDTO;
import com.spring.mvc.chap05.entity.Board;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class BoardRepositoryImpl implements BoardRepository{

    private final static Map<Integer, Board> boardMap;
    private static int sequence;

    static {
        boardMap = new HashMap<>();
    }

    @Override
    public List<Board> findAll() {
        return boardMap.values()
                .stream()
                .sorted(Comparator.comparing(Board::getBoardNo).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Board findOne(int boardNo) {
        return boardMap.get(boardNo);
    }

    public boolean save(WriteDTO dto) {
        Board board = new Board(++sequence, dto.getTitle(), 0, dto.getTextContent(), LocalDateTime.now());
        boardMap.put(board.getBoardNo(), board);
        return true;
    }

    @Override
    public boolean deleteByNo(int boardNo) {
        return false;
    }
}
