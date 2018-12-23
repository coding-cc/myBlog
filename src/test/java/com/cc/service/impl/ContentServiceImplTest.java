package com.cc.service.impl;

import com.cc.BlogApplicationTests;
import com.cc.model.entity.Contents;
import com.cc.repository.ContentRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class ContentServiceImplTest extends BlogApplicationTests {

    @Autowired
    private ContentRepository repository;

    @Test
    public void findAllContents() {
        List<Contents> all = repository.findAll();
        Assert.assertNotNull(all);
    }

    @Test
    public void findById() {
        Optional<Contents> res = repository.findById(2);
        Assert.assertNotNull(res);
    }

    public static void main(String[] args) {
        String []s=null;
        System.out.println(s.length);
    }
}