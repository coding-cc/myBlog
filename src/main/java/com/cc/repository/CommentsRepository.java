package com.cc.repository;

import com.cc.model.entity.Comments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : cc
 * @date : 2018-09-25  19:43
 */
@Repository
public interface CommentsRepository extends JpaRepository<Comments, Integer> {

    /**
     * 查找某用户收到的所有留言
     * @param id 用户唯一标识
     * @param pageable 分页
     * @return 返回集合
     */
    Page<Comments>findByAuthorId(Integer id, Pageable pageable);

    /**
     * 查找某篇文章下的所有评论
     * @param id 文章唯一标识
     * @return 集合
     */
    List<Comments>findByCid(Integer id);

    /**
     * 查找某篇文章下的所有评论 带分页条件
     * @param id 文章唯一标识
     * @param pageable 分页
     * @return 集合
     */
    Page<Comments> findByCid(Integer id, Pageable pageable);

    /**
     * 删除
     * @param coid 主键
     */
    void deleteByCoid(Integer coid);

}
