package com.cc.service.impl;

import com.cc.model.entity.Relationships;
import com.cc.repository.RelationshipRepository;
import com.cc.service.RelationshipsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : cc
 * @date : 2019-03-30  11:28
 */
@Service
public class RelationshipsServiceImpl implements RelationshipsService {

    @Autowired
    private RelationshipRepository relationshipRepository;

    @Override
    public Relationships save(Relationships relationships) {
        return relationshipRepository.save(relationships);
    }
}
