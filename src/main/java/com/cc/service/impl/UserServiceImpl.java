package com.cc.service.impl;

import com.cc.exception.BlogException;
import com.cc.model.entity.Users;
import com.cc.repository.UserRepository;
import com.cc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author : cc
 * @date : 2018-09-25  11:37
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public Users findUserById(Integer id) {
        Optional<Users> usersOptional = repository.findById(id);
        if(!usersOptional.isPresent()){
            throw new BlogException("用户不存在");
        }
        return usersOptional.get();
    }

    @Override
    public Users findUserByName(String username) {
        Users user = repository.findByUsername(username);
        /*if(null == user){
            throw new BlogException("用户不存在");
        }*/
        return user;
    }

    @Override
    public void save(Users users) {
        repository.save(users);
    }
}
