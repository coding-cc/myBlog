package com.cc.model.entity;


import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 元数据
 *
 * @author : cc
 */
@Data
@Table(name = "t_metas")
@Entity
public class Metas{

    /**
     * 项目主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mid;

    /**
     * 项目名称
     */
    private String  name;

    /*
     * 项目缩略名
     * private String  slug;
     */


    /**
     * 项目类型
     */
    private String  type;

    /**
     * 项目描述
     */
    private String  description;

    /**
     * 项目排序
     */
    private Integer sort;

    /**
     * 父级
     */
    private Integer parent;

    /**
     * 项目下文章数
     */
    @Ignore
    @Transient
    private Integer count;

}