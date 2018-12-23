package com.cc.model.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * 配置选项
 *
 * @author : cc
 */
@Data
@Table(name = "t_options")
public class Options{

    /**
     * 配置键
     */
    private String name;

    /**
     * 配置值
     */
    private String value;

    /**
     * 配置描述
     */
    private String description;

}