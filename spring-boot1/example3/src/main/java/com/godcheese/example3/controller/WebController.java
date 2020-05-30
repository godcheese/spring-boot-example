package com.godcheese.example3.controller;

import com.godcheese.example3.entity.UserEntity;
import com.godcheese.example3.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/9 16:37
 */
@Controller
@RequestMapping("/web")
public class WebController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping({"/index"})
    public String index(){
        return "/index";
    }

    @RequestMapping("/login")
    public String login(){
        return "/login";
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping("/user_center")
    public String userCenter(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("authorities",authentication.getAuthorities());
        return "/user_center";
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
        return "/403";
    }
}
