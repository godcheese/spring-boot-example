package com.godcheese.example3.entity;

import java.io.Serializable;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/5 17:44
 */
public class RoleEntity implements Serializable {

    private static final long serialVersionUID = -3436705645658470819L;

    private Long id;
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
