package com.cc.service;

import com.cc.model.entity.Attach;

import java.util.List;

/**
 * @author : cc
 * @date : 2018-09-25  19:54
 */
public interface AttachService {
    List<Attach>findAllByAuthid(Integer id);
}
