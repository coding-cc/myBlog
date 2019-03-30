package com.cc.service;

import com.cc.model.entity.Metas;

/**
 * @author : cc
 * @date : 2018-11-23  10:43
 */
public interface MetaService {
    /**
     * 查看是否存在名为 name 的记录
     * @param name
     * @return
     */
    boolean exist(String name);

    /**
     * 保存
     * @param metas
     * @return
     */
    Metas save(Metas metas);

    /**
     * 根据标签名查id
     * @param name
     * @return
     */
    Metas findByName(String name);

}
