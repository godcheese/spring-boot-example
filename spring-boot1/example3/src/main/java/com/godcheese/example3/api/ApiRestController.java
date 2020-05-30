package com.godcheese.example3.api;

import com.godcheese.example3.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Iterator;


/**
 * @author godcheese [godcheese@outlook.com]
 * @date
 */
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api")
@RestController
public class ApiRestController {

    @Autowired
    private UserMapper userMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/download", method = RequestMethod.GET)
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
    @RequestMapping(value = "/user/delete/{id}", method = RequestMethod.POST)
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
