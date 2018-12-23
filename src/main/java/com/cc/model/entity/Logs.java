package com.cc.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 日志记录
 *
 * @author : cc
 */
@Data
@AllArgsConstructor
@Entity
@Table(name = "t_logs")
public class Logs{

    /**
     * 日志主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 产生的动作
     */
    private String action;

    /**
     * 产生的数据
     */
    private String data;

    /**
     * 发生人id
     */
    private Integer authorId;

    /**
     * 日志产生的ip
     */
    private String ip;

    /**
     * 日志创建时间
     */
    private Date created;

    public Logs(String action, String data, String ip, Integer uid) {
        this.action = action;
        this.data = data;
        this.ip = ip;
        this.authorId = uid;
        this.created = new Date();
    }

}