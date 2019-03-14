package com.cc.service.impl;

import com.cc.model.entity.Attach;
import com.cc.repository.AttachRepository;
import com.cc.service.AttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Page<Attach> findAllByAuthid(Integer id, Pageable pageable) {
        return attachRepository.findByAuthorId(id, pageable);
    }

    @Override
    public void save(Attach attach) {
        attachRepository.save(attach);
    }

    @Override
    public void deleteOne(Integer id) {
        attachRepository.deleteById(id);
    }
}
