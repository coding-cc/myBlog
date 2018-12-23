package com.cc.service;

import com.cc.BlogApplicationTests;
import com.cc.model.entity.Comments;
import com.cc.repository.CommentsRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.Assert.*;

public class CommentsServiceTest extends BlogApplicationTests {

    @Autowired
    private CommentsRepository repository;

    @Test
    public void findAllByArticleId() {
        Page<Comments> result = repository.findByCid(1, PageRequest.of(0, 6));
        Assert.assertNotEquals(0, result.getContent().size());
    }
}