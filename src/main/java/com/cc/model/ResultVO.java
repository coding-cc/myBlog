package com.cc.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : cc
 * @date : 2018-10-02  20:02
 */
@Data
public class ResultVO<T> implements Serializable {

    private Integer code;
    private String msg;
    private T data;

}
