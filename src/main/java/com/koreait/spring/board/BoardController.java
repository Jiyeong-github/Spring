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
    public String detail(BoardDTO param, Model model) {
        System.out.println("iboard:" + param.getIboard());
        BoardDomain data = service.selBoard(param);
        model.addAttribute("data", data);
        return "board/detail";
    }

    @ResponseBody //return해주는 걸 무조건 문자열로(json 형태)
    @RequestMapping(value = "/cmt", method = RequestMethod.POST)
    public Map<String, Integer> cmtIns(@RequestBody BoardCmtEntity param) {
        System.out.println("param=" + param);
        int result = service.insBoardCmt(param);
        Map<String, Integer> data = new HashMap();
        data.put("result", result);

        return data;
    }

    @ResponseBody
    @RequestMapping("/cmt/{iboard}")
    public List<BoardCmtDomain> cmtSel(@PathVariable int iboard) {
        BoardCmtEntity param = new BoardCmtEntity();
        param.setIboard(iboard);
        return service.selBoardCmtList(param);
    }

    @ResponseBody
    @RequestMapping(value = "/cmt/{icmt}", method = RequestMethod.DELETE)
    public Map<String, Integer> cmtDel(BoardCmtEntity param, @PathVariable int icmt) {
        param.setIcmt(icmt);

        int result = service.delBoardCmt(param);

        Map<String, Integer> data = new HashMap();
        data.put("result", result);

        return data;
    }

    @ResponseBody
    @RequestMapping(value = "/cmt", method = RequestMethod.PUT)
    public Map<String, Integer> cmtUpd(@RequestBody BoardCmtEntity param) {
        int result = service.updBoardCmt(param);
        Map<String, Integer> data = new HashMap();
        data.put("result", result);
        return data;
    }

    @GetMapping("/writeMod")
    public void writeMod() {
    }

    @PostMapping("writeMod")
    public String writeMod(BoardEntity param) {
        int iboard = service.writeMod(param);
        return "redirect:detail?iboard=" + iboard;
    }
}
