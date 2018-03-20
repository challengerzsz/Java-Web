package com.bsb.service.impls;

import com.bsb.pojo.User;
import com.bsb.service.IUserService;

import java.util.List;

public class UserServiceImpl implements IUserService{
    @Override
    public User login(String username, String password) {
        return null;
    }

    @Override
    public User register(String username, String password, String confirmedPassword) {
        return null;
    }

    @Override
    public boolean updateUser(int id) {
        return false;
    }

    @Override
    public User queryUser(int id) {
        return null;
    }

    @Override
    public List<User> queryAllUsers() {
        return null;
    }
}
