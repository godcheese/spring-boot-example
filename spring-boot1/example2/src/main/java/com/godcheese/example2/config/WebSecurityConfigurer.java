package com.godcheese.example2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/7 19:00
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private SessionRegistry sessionRegistry;

    @Bean
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }

    @Autowired
    @Qualifier("simpleUserDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // @formatter:off
        http
                // 禁用 csrf
//                .csrf().disable()

                // 禁用 ROLE_ANONYMOUS 角色
//                .anonymous().disable()

                .authorizeRequests()

                // 静态资源 url ，无需登录认证权限
                .antMatchers("robots.txt", "/**.ico", "/assets/**", "/css/**", "/js/**", "/img/**", "/vendor/**").permitAll()

                // 指定 url ，无需登录认证权限
                .antMatchers("/","/index").permitAll()

                // 其它请求均需要认证 与 .antMatchers("/**").authenticated() 等效
                .anyRequest().authenticated()

                // 开启表单登录，设置登录 url
                .and().formLogin().loginPage("/login")

                // 自定义登录表单提交 url
                .loginProcessingUrl("/login/form")

                // 设置登录成功跳转到的 url
                .defaultSuccessUrl("/user_center",true)

                // 设置登录失败跳转到的 url
                .failureUrl("/login?error").permitAll()

                // 开启记住我功能， cookie 保存登录数据
                .and().rememberMe().rememberMeParameter("rememberMe")

                // 设置 cookie 有效期，7天内有效
                .tokenValiditySeconds(60 * 60 * 60 * 24 * 7)

                // 设置cookie的私钥
                .key("user")

                .and().logout()

                // 设置注销 url
                .logoutUrl("/logout")

                //设置注销成功后跳转页面，默认是跳转到登录页面
                .logoutSuccessUrl("/login?logout").permitAll()

                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)

                // 限制单个用户最大session，只允许一个用户登录
//                .maximumSessions(1)

                // session 过期后跳转到登录 url
//                .expiredUrl("/login")

                // 动态修改权限立即生效，未测试
//                .sessionRegistry(sessionRegistry)

                // 报错处理，访问指定 url
                .and().exceptionHandling().accessDeniedPage("/403");
        // @formatter:on
    }
}
