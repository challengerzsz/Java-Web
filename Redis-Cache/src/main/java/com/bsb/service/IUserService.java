package com.bsb.service;

import com.bsb.common.ServerResponse;
import com.bsb.pojo.User;

import java.util.List;

/**
 * @Author: zeng
 * @Date: 2018/7/24 10:45
 */
public interface IUserService {

    ServerResponse<String> insertUser(String username, String password);

    ServerResponse<List<User>> listUsers();

}
