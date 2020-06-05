package com.godcheese.example3.entity;

import java.io.Serializable;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2020-06-04
 */
public class GradeEntity implements Serializable {

    private static final long serialVersionUID = -4368467676097826034L;

    private Long id;

    /**
     * 年级名
     */
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}