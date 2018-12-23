package com.cc.service;

import com.cc.model.entity.Comments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author : cc
 * @date : 2018-09-25  19:45
 */
public interface CommentsService {

    /**
     * 查找某用户收到的所有留言
     * @param id 用户唯一标识
     * @param pageRequest 分页
     * @return 返回集合
     */
    Page<Comments>findAllByAuId(Integer id, PageRequest pageRequest);

    /**
     * 添加评论
     * @param comments 评论对象
     * @return 返回的对象
     */
    Comments add(Comments comments);

    /**
     * 查找某篇文章下的所有评论
     * @param id 文章唯一标识
     * @return 集合
     */
    List<Comments>findAllByArticleId(Integer id);

    /**
     * 查找某篇文章下的所有评论 带分页
     * @param id 文章唯一标识
     * @param pageable 分页
     * @return 集合
     */
    Page<Comments> findAllByArticleId(Integer id, Pageable pageable);

    /**
     * 删除评论
     * @param coid 主键
     */
    void deleteOne(Integer coid);

    void save(Comments comments);

    Optional findById(Integer coid);
}
