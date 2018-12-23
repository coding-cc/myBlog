package com.cc.repository;

import com.cc.model.entity.Logs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author : cc
 * @date : 2018-09-25  15:42
 */
@Repository
public interface LogRepository extends JpaRepository<Logs, Integer> {

//    @Query(value = "SELECT logs FROM Logs logs WHERE logs.authorId = :uid GROUP BY logs.created")
//    List<Logs>findFiveByUid(@Param(value = "uid") Integer uid);

    Page<Logs> findLogsByAuthorId(Integer uid, Pageable pageable);
}
