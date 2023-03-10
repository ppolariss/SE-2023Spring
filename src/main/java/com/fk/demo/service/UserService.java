package com.fk.demo.service;

import com.fk.demo.dao.UserDao;
import com.fk.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao userDao;


    public User getUsernameAndPassword(String username, String password) {
        return userDao.getByUsernameAndPassword(username, password);
    }

    public void addOrUpdate(User user) {
        userDao.save(user);
    }

    public boolean isUsernameExist(String username) {
        User user = getByName(username);
        return null != user;
    }

    public boolean isIdcardExist(String Idcard) {
        User user = getByIdcard(Idcard);
        return null != user;
    }

    public boolean isEmailExist(String email) {
        User user = getByemail(email);
        return null != user;
    }

    public boolean isPhoneExist(String phone) {
        User user = getByphone(phone);
        return null != user;
    }

    public User getByName(String username) {
        return userDao.findByUsername(username);
    }

    public User getByIdcard(String Idcard) {
        return userDao.findByIdcard(Idcard);
    }

    public User getByemail(String email) {
        return userDao.findByEmail(email);
    }

    public User getByphone(String phone) {
        return userDao.findByPhone(phone);
    }
}
