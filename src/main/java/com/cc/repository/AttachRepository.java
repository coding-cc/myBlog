package com.cc.repository;

import com.cc.model.entity.Attach;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : cc
 * @date : 2018-09-25  19:52
 */
public interface AttachRepository extends JpaRepository<Attach, Integer> {
    List<Attach>findByAuthorId(Integer id);
    Page <Attach> findByAuthorId(Integer id, Pageable pageable);
}
