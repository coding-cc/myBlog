package com.cc.model.entity;

import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 文章
 *
 * @author : cc
 */
@Data
@Entity
@Table(name = "t_contents")
public class Contents{

    /**
     * 文章表主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章缩略名
     */

    private String slug;

    /**
     * 文章创建时间戳
     */
    private Date created;

    /**
     * 文章修改时间戳
     */
    private Date modified;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章创建用户
     */
    private Integer authorId;

    /**
     * 文章点击次数
     */
    private Integer hits;

    /**
     * 文章类型： PAGE、POST
     */
    private String type;

    /**
     * 内容类型，markdown或者html
     */
    private String fmtType;

    /**
     * 文章缩略图
     */
    private String thumbImg;

    /**
     * 标签列表
     */
    private String tags;

    /**
     * 分类列表
     */
    private String categories;

    /**
     * 内容状态
     */
    private String status;

    /**
     * 内容所属评论数
     */
    private Integer commentsNum;

    /**
     * 是否允许评论
     */
    private Boolean allowComment;

    /**
     * 是否允许ping
     */
    private Boolean allowPing;

    /**
     * 允许出现在Feed中
     */
    private Boolean allowFeed;

//    @Ignore

    @Transient
    private String url;
}