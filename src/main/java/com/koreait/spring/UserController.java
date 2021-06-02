package com.koreait.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.jws.soap.SOAPBinding;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired //자동으로 연결시켜 줌
    private UserService service;

    @RequestMapping("/login")
    public String login(){
        return "user/login";
    }

    @RequestMapping(value="/join")
    public String join(){
        return "user/join";
    }

    @RequestMapping(value="/join", method = RequestMethod.POST)
    public String join(UserEntity param)
    {   System.out.println(param);
        service.join(param);

        return "redirect:/join";
    }
}
