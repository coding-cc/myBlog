package com.cc.model.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author : cc
 *
 */
@Data
@Entity
@Table(name = "t_attach")
public class Attach{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String  fname;
    private String  ftype;
    private String  url;
    private Integer authorId;
    private Date created;

}
