package com.cc.repository;

import com.cc.BlogApplicationTests;
import com.cc.model.entity.Contents;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ContentRepositoryTest extends BlogApplicationTests {

    @Autowired
    private ContentRepository repository;

    @Test
    public void findAllContentTest(){
        List<Contents> all = repository.findAll();
//        Contents all = repository.findById(2).get();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long lt = new Long("1538830157");
        Date date = new Date(lt);
        System.out.println(simpleDateFormat.format(date));

        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(1487861184),ZoneId.systemDefault())));
        Assert.assertNotNull(all);

    }

    @Test
    public void timeTest(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long lt = new Long("1538830157");
        Date date = new Date(lt);
        System.out.println(simpleDateFormat.format(date));

        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(1538830157),ZoneId.systemDefault())));
    }

}