package com.cc.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 数据关系
 *
 * @author : cc
 */
@Data
@Entity
@Table(name = "t_relationships")
public class Relationships{

    /**
     * 文章主键
     */
    @Id
    private Integer cid;

    /**
     * 项目主键
     */
    private Integer mid;

}