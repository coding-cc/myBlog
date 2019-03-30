package com.cc.repository;

import com.cc.model.entity.Contents;
import com.cc.model.entity.Metas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : cc
 * @date : 2018-09-18  13:04
 */
@Repository
public interface ContentRepository extends JpaRepository<Contents, Integer> {

    /**
     * 查找某个用户的所有文章
     * @param id 用户id
     * @return 集合
     */
    List<Contents>findByAuthorId(Integer id);

    /**
     * 查找某个用户的所有文章 带分页条件和排序
     * @param id 用户id
     * @param pageable 分页
     * @return 集合
     */
    Page<Contents> findByAuthorId(Integer id, Pageable pageable);

    /**
     * 根据类型查找某个用户的所有文章 带分页条件和排序
     * @param id 用户id
     * @param pageable 分页
     * @param type 类型 post page
     * @return 集合
     */
    Page<Contents> findByAuthorIdAndTypeEquals(Integer id, String type, Pageable pageable);

    /**
     * 根据类型查找某个用户的所有文章 带分页条件和排序
     * @param id 用户id
     * @param type 类型 post page
     * @return 集合
     */
    List<Contents> findByAuthorIdAndTypeEquals(Integer id, String type);

    /**
     * 删除
     * @param cid 主键
     */
    void deleteByCid(Integer cid);

    /**
     * 模糊查询 -- 标题或者内容
     * @param keyword 关键字
     * @return 结果集
     */
    Page<Contents> findByTitleLike(String keyword, Pageable pageable);

    /**
     * 查询tag为XX的文章总数
     * @param tag
     * @return
     */
    int countByTags(String tag);

    /**
     * 查询category为XX的文章总数
     * @param tag
     * @return
     */
    int countByCategories(String tag);

    /**
     * category为指定的所有的Contents
     * @param category
     * @return
     */
    Page<Contents> findByCategories(String category, Pageable pageable);

    /**
     * @param mid
     * @return
     */
    @Query(value = "select c from Contents c where c.cid in (select r.cid from Relationships r where r.mid=?1)")
    Page<Contents> pageByMetaId(Integer mid, Pageable pageable);



}
