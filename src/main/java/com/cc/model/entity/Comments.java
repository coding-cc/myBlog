package com.cc.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 评论
 *
 * @author : cc
 */
@Entity
@Data
@Table(name = "t_comments")
public class Comments{

     /**comment表主键*/
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer coid;

     /**post表主键,关联字段*/
    private Integer cid;

     /**评论生成时的GMT unix时间戳*/
    private Date created;

     /**评论作者*/
    private String author;

    /**评论所属内容作者id*/
    private Integer authorId;

    /**评论所属用户id*/
    private Integer ownerId;

     /**评论者邮件*/
    private String mail;

     /**评论者网址*/
    private String url;

     /**评论者ip地址*/
    private String ip;

     /**评论者客户端*/
    private String agent;

     /**评论内容*/
    private String content;

     /**评论类型*/
    private String type;

     /**评论状态*/
    private String status;

     /**父级评论*/
    private Integer parent;

}