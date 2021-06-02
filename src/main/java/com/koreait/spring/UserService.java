package com.koreait.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    public UserMapper mapper;

    public int join(UserEntity param) {
        return mapper.insUser(param);
    }
}
