package com.cc.service.impl;

import com.cc.model.entity.Attach;
import com.cc.repository.AttachRepository;
import com.cc.service.AttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : cc
 * @date : 2018-09-25  19:54
 */
@Service
public class AttachServiceImpl implements AttachService {

    @Autowired
    private AttachRepository attachRepository;

    @Override
    public List<Attach> findAllByAuthid(Integer id) {
        return attachRepository.findByAuthorId(id);
    }
}
