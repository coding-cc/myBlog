package com.cc.service;

import com.cc.model.entity.Users;

/**
 * @author : cc
 * @date : 2018-09-25  11:35
 */
public interface UserService {

    /**
     * 根据主键查询用户
     * @param id
     * @return
     */
    Users findUserById(Integer id);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    Users findUserByName(String username);

    void save(Users users);

}
