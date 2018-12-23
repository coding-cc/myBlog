package com.cc.controller;

import com.cc.model.entity.TestU;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/**
 * @author : cc
 * @date : 2018-10-07  11:50
 */
@RestController
public class TestResController {

    @GetMapping("/cc/test")
    public TestU get(){
        return new TestU();
    }

}
