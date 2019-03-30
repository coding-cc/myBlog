package com.cc.repository;

import com.cc.model.entity.Metas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : cc
 * @date : 2018-11-23  10:26
 */
public interface MetaRepository extends JpaRepository<Metas, Integer> {
    /**
     * 是否存在名为name的记录
     * @param name
     * @return 存在吗
     */
    boolean existsByName(String name);

    Metas findByName(String name);

}
