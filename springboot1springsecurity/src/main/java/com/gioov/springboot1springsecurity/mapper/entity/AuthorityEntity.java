package com.gioov.springboot1springsecurity.mapper.entity;

import java.io.Serializable;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/7 19:48
 */
public class AuthorityEntity implements Serializable {

    private static final long serialVersionUID = 8583837227582969043L;

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

    @Override
    public String toString() {
        return "AuthorityEntity{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
