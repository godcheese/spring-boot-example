package com.gioov.springboot1springsecurity.controller;

import com.gioov.springboot1springsecurity.mapper.UserMapper;
import com.gioov.springboot1springsecurity.mapper.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/7 18:49
 */
@Controller
public class SystemController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping({"/","/index"})
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping("/userCenter")
    public String userCenter(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("authorities",authentication.getAuthorities());
        return "userCenter";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/admin")
    public String admin(Model model){
        List<UserEntity> userEntityList = userMapper.listAll();
        model.addAttribute(userEntityList);
        return "admin";
    }

    @RequestMapping("/403")
    public String error403(){
        return "403";
    }

    @PreAuthorize("hasRole('SYSTEM_ADMIN') OR hasAuthority('/user/delete/{id}')")
    @PostMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        userMapper.deleteOne(id);
        return "redirect:/admin";
    }

}