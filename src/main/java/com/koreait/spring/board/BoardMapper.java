package com.koreait.spring.board;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDomain> selBoardList(); //객체 옆 ID값();
    BoardDomain selBoard(BoardDTO param);
    BoardDomain delBoard(BoardDTO param);
}
