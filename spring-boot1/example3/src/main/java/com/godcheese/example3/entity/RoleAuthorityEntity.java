package com.godcheese.example3.entity;

import java.io.Serializable;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/7 19:47
 */
public class RoleAuthorityEntity implements Serializable {

    private static final long serialVersionUID = 569297315533520782L;

    private Long id;
    private Long roleId;
    private Long authorityId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }
}
