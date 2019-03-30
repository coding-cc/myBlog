package com.cc.repository;

import com.cc.model.entity.Metas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : cc
 * @date : 2018-11-18  17:47
 */
public interface CategoryRepository extends JpaRepository<Metas, Integer> {


    /**
     * 搜索
     * @param type
     * @return
     */
    List<Metas> findAllByType(String type);

}
