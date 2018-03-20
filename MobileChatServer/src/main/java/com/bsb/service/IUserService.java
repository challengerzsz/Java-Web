package com.bsb.service;

import com.bsb.pojo.User;

import java.util.List;

public interface IUserService {

    User login(String username, String password);

    User register(String username, String password, String confirmedPassword);

    boolean updateUser(int id);

    User queryUser(int id);

    List<User> queryAllUsers();
}
