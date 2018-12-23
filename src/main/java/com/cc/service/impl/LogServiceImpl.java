package com.cc.service.impl;

import com.cc.model.entity.Logs;
import com.cc.repository.LogRepository;
import com.cc.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : cc
 * @date : 2018-09-25  15:46
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository repository;

    @Override
    public Page<Logs> findPreFiveByUid(Integer uid, Pageable pageable) {
        return repository.findLogsByAuthorId(uid, pageable);
    }

    @Override
    public Logs save(Logs logs) {
        return repository.save(logs);
    }
}
