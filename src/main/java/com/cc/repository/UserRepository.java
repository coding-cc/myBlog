package com.cc.repository;

import com.cc.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : cc
 * @date : 2018-09-25  11:33
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    /**
     * 根据用户名查询
     * @param username 用户名
     * @return
     */
    Users findByUsername(String username);

}
