package com.koreait.spring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired //자동으로 연결시켜 줌
    private UserService service;

    @RequestMapping(value = "/login")
    //requestParam은 getparameter
    public String login(@RequestParam(value = "err", required = false
            , defaultValue = "0") int err, Model model) {
        //err 0 들어가면 error 안 난 거
        switch (err) {
            case 1: //아이디 없음
                model.addAttribute("errMsg", "아이디를 확인해주세요");
                break;
            case 2: //비밀번호 틀림
                model.addAttribute("errMsg", "비밀번호를 확인해주세요");
                break;
        }
        System.out.println("err:" + err);
        return "user/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(UserEntity param) {
        System.out.println(param);
        return "redirect:" + service.login(param);
    }

    @RequestMapping(value = "/join")
    public String join() {
        return "user/join";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String join(UserEntity param) {
        System.out.println(param);
        service.join(param);

        return "redirect:/user/join";
    }

//    @RequestMapping("/profile")
//    public String profile() {
//        return "user/profile";
//    }

    @RequestMapping("/logout")
    public String logout(HttpSession hs, HttpServletRequest req) {
        hs.invalidate();
        String referer = req.getHeader("Referer");
        return "redirect:" + referer;
    }

    @GetMapping("/profile")
    public void profile() {
    }

    //    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    @PostMapping("profile")
    public String profile(MultipartFile profileImg) {
        return "redirect:" + service.uploadProfile(profileImg);
    }

}
