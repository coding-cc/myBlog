package com.cc.service;

import com.cc.model.entity.Metas;

import java.util.List;

/**
 * @author : cc
 * @date : 2018-11-18  17:46
 */
public interface CategoryService {

    /**
     * 查找所有Meta(标签or类别)
     * @param type tag or category
     * @return result
     */
    List<Metas> findAllByType(String type);

}
