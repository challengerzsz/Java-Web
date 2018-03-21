package com.bsb.service.impls;

import com.bsb.dao.impls.UserDao;
import com.bsb.pojo.User;
import com.bsb.service.IUserService;
import com.bsb.utils.MatchUtil.MatchRegexUtil;

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
    public boolean register(String username, String password, String confirmedPassword, String email) throws SQLException {
        System.out.println("调用service");
        if (password.equals(confirmedPassword) && MatchRegexUtil.checkEmail(email)) {
            return userDao.register(username, password, email);
        }

        return false;
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
