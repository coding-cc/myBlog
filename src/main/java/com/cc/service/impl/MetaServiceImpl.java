package com.cc.service.impl;

import com.cc.model.entity.Metas;
import com.cc.repository.MetaRepository;
import com.cc.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author : cc
 * @date : 2018-11-23  10:44
 */
@Service
public class MetaServiceImpl implements MetaService {

    @Autowired
    private MetaRepository repository;

    @Override
    public boolean exist(String name) {
        return repository.existsByName(name);
    }

    @Override
    public Metas save(Metas metas) {
        return repository.save(metas);
    }

    @Override
    public Metas findByName(String name) {
        return repository.findByName(name);
    }

}
