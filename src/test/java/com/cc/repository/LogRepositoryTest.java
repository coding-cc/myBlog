package com.cc.repository;

import com.cc.BlogApplicationTests;
import com.cc.model.entity.Logs;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.Assert.*;

public class LogRepositoryTest extends BlogApplicationTests {

    @Autowired
    private LogRepository repository;

    @Test
    public void findFiveByUid() {
        Page<Logs> fiveByUid = repository.findLogsByAuthorId(1, PageRequest.of(
                0, 5, new Sort(Sort.Direction.ASC, "created")));
        Gson gson = new Gson();
        String s = gson.toJson(fiveByUid);
        System.out.println(s);
        Assert.assertNotEquals(fiveByUid.getContent().size(), 0);
    }
}