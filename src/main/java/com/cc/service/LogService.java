package com.cc.service;

import com.cc.model.entity.Logs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author : cc
 * @date : 2018-09-25  15:43
 */
public interface LogService {
    /**
     * 根据userId查询前5条日志 根据时间
     * @param uid
     * @param pageable
     * @return page
     */
    Page<Logs> findPreFiveByUid(Integer uid, Pageable pageable);

    Logs save(Logs logs);
}
