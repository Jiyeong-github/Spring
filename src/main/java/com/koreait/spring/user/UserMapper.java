package com.koreait.spring.user;

import org.apache.ibatis.annotations.Mapper;

//DAO 생성
@Mapper
public interface UserMapper {
    int insUser(UserEntity param);
    UserEntity selUser(UserEntity param);
}
