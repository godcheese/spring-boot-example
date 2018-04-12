package com.gioov.springboot1springsecurityoauth2.api;

import com.gioov.springboot1springsecurityoauth2.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Iterator;


/**
 * @author godcheese [godcheese@outlook.com]
 * @date
 */
@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api")
public class ApiRestController {

    @Autowired
    private UserMapper userMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/download")
    public String download(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        StringBuilder stringBuilder = new StringBuilder();
       stringBuilder
               .append("admin or system_admin 都可以访问，下载成功")
               .append("<br/>")
               .append("你的当前所在角色和所拥有权限：")
               .append("<br/>");
        Collection authorityList =  authentication.getAuthorities();
        for(Iterator it = authorityList.iterator();it.hasNext();){
            SimpleGrantedAuthority authority = (SimpleGrantedAuthority) it.next();
            stringBuilder.append(authority.getAuthority()).append(",");
        }

        return stringBuilder.toString();
    }

    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @PostMapping("/user/delete/{id}")
    public Integer deleteUser(@PathVariable Long id){
        try {
            userMapper.deleteOne(id);
        } catch (Exception e){
            e.printStackTrace();

            // 删除失败
            return 404;
        }

        // 删除成功
        return 200;
    }

}
