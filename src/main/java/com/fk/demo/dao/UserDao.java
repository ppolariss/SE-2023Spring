package com.fk.demo.dao;

import com.fk.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findByIdcard(String idcard);

    User findByPhone(String phone);

    User findByEmail(String email);

    User getByUsernameAndPassword(String username, String password);

//    User findByUsernameOrIdcardOrPhoneOrEmail(String username, String idcard, String phone, String email);
}
