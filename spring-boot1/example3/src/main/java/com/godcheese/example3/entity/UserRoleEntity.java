package com.godcheese.example3.entity;

import java.io.Serializable;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/5 18:08
 */
public class UserRoleEntity implements Serializable {

    private static final long serialVersionUID = -970573874221410168L;

    private Long id;
    private Long userId;
    private Long roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
