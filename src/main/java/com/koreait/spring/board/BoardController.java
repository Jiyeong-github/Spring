package com.koreait.spring.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller //주소가 들어왔을 때 호출시켜줌
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService service;

    @RequestMapping("/list")
    public String list(Model model) {
        List<BoardDomain> list = service.selBoardList();
        model.addAttribute("list", list); //request에 담음 - > forward 해야해서
        return "board/list";
    }

    @RequestMapping("/detail")
    public String detail(BoardDTO param, Model model){
        System.out.println("iboard:"+param.getIboard());
        BoardDomain data = service.selBoard(param);
        model.addAttribute("data",data);
        return "board/detail";
    }

    @ResponseBody //return해주는 걸 무조건 문자열로(json 형태)
    @RequestMapping(value="/cmtInsSel", method= RequestMethod.POST)
    public Map<String,Integer> cmtInsSel(@RequestBody BoardCmtEntity param){
        Map<String,Integer> data = new HashMap();
        List<String> list = new ArrayList<>();

        data.put("result",1);
        data.put("age",30);

        return data;
    }
}
