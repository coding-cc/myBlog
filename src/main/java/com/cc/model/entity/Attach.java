package com.cc.model.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author : cc
 *
 */
@Data
@Entity
@Table(name = "t_attach")
public class Attach{

    @Id
    private Integer id;
    private String  fname;
    private String  ftype;
    private String  fkey;
    private Integer authorId;
    private Integer created;

}
