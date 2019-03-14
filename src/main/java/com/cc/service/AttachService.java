package com.cc.service;

import com.cc.model.entity.Attach;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author : cc
 * @date : 2018-09-25  19:54
 */
public interface AttachService {

    List<Attach> findAllByAuthid(Integer id);

    Page<Attach> findAllByAuthid(Integer id, Pageable pageable);

    void save(Attach attach);

    void deleteOne(Integer cid);
}
