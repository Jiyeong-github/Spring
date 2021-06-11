package com.koreait.spring.board;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDomain> selBoardList(BoardDTO param); //객체 옆 ID값();
    BoardDomain selBoard(BoardDTO param);
    int selMaxPageVal(BoardDTO param);
    int delBoard(BoardEntity param);
    int insBoard(BoardEntity param);
    int updBoard(BoardEntity param);
}
