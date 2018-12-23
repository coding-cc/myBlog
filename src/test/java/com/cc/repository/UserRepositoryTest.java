package com.cc.repository;

import com.cc.BlogApplicationTests;
import com.cc.model.entity.Users;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UserRepositoryTest extends BlogApplicationTests {

    @Autowired
    private UserRepository repository;

    @Test
    public void findOneTest(){
        Users users = repository.findById(1).get();
        Assert.assertNotNull(users);
    }

    @Test
    public void findByNameTest(){
        Users cc = repository.findByUsername("cc");
        Assert.assertNotNull(cc);
    }

}