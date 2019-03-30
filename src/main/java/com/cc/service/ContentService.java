package com.cc.service;

import com.cc.model.entity.Contents;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author : cc
 * @date : 2018-09-18 13:34
 */
public interface ContentService {
    /**
     * 查找某人所有内容
     * @param id User.id
     * @return 文章集合
     */
    @Deprecated
    List<Contents>findAllByAuthorId(Integer id);

    /**
     * 查找某个用户的所有内容 带分页和排序
     * @param id 用户id
     * @param pageable 分页
     * @return 分页数据
     */
    Page<Contents> findAllByAuthorId(Integer id, Pageable pageable);

    /**
     * 根据类型查找某个用户的所有文章 带分页和排序
     * @param id 用户id
     * @param pageable 分页
     * @param type 文章的类型 post AND page
     * @return 分页数据
     */
    Page<Contents> findAllByAuthorIdAndType(Integer id, String type, Pageable pageable);

    /**
     * 根据类型查找某个用户的所有文章
     * @param id 用户id
     * @param type 文章的类型 post AND page
     * @return 分页数据
     */
    List<Contents> findAllByAuthorIdAndType(Integer id, String type);


    /**
     * 查找某篇文章
     * @param cid 唯一标识
     * @return Contents
     */
    Contents findById(Integer cid);

    Contents addOne(Contents contents);

    Contents save(Contents content);

    void deleteOne(Integer cid);

    Page<Contents> findByKeyword(String keyword, Pageable pageable);

    int countByTag(String tag);

    int countByCategory(String category);

    Page<Contents> pageByCategory(String category, Pageable pageable);

    Page<Contents> pageByMetaId(Integer mid, Pageable pageable);
}
