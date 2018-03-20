package com.bsb.service.impls;

import com.bsb.dao.impls.UserDao;
import com.bsb.pojo.User;
import com.bsb.service.IUserService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserService implements IUserService{
    private UserDao userDao = new UserDao();

    public UserService() throws IOException {
    }

    @Override
    public User login(String username, String password) throws SQLException {
        return userDao.login(username, password);
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
