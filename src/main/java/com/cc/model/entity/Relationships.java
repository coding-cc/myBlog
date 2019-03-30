package com.cc.model.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 数据关系
 *
 * @author : cc
 */
@Data
@Entity
@Table(name = "t_relationships")
public class Relationships{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 文章主键
     */
    private Integer cid;

    /**
     * 项目主键
     */
    private Integer mid;

}
