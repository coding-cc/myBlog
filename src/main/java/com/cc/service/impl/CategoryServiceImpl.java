package com.cc.service.impl;

import com.cc.model.entity.Metas;
import com.cc.repository.CategoryRepository;
import com.cc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : cc
 * @date : 2018-11-18  18:04
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Override
    public List<Metas> findAllByType(String type) {
        return repository.findAllByType(type);
    }
}
