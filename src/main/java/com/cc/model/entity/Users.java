package com.cc.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 用户
 *
 * @author : cc
 */
@Data
@Entity
@Table(name = "t_users")
public class Users{

    /**
     * user表主键
     */
    @Id
    private Integer uid;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户的邮箱
     */
    private String email;

    /**
     * 用户的主页
     */
    private String homeUrl;

    /**
     * 用户显示的名称
     */
    private String screenName;

    /**
     * 用户注册时间
     */
    private Date created;

    /**
     * 最后活动时间
     */
    private Date activated;

    /**
     * 上次登录最后活跃时间
     */
    private Integer logged;

    /**
     * 用户组
     */
    private String groupName;

}