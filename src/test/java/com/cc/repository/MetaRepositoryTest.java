package com.cc.repository;

import com.cc.BlogApplicationTests;
import com.cc.model.entity.Metas;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class MetaRepositoryTest extends BlogApplicationTests {

    @Autowired
    private MetaRepository metaRepository;
}
