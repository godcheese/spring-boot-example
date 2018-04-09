
# [springboot1springsecurity](https://github.com/godcheese/springboot-example/tree/master/springboot1springsecurity)
> Spring Boot 1.x 整合 Spring Security 实现角色/权限控制实例

- [官方实现](http://spring.io/guides/gs/securing-web/)

|环境  |版本|
|:-----|---|
|Java  |1.8|
|MySQL |5.7|
|Maven |3.5|
|Tomcat|8.5|

|依赖            |  版本        |
|:------------- |:-------------|
|Spring Boot    |1.5.11.RELEASE|
|Spring Security|4.2.5.RELEASE | 
|MyBatis        |3.4.6         |
|Thymeleaf      |2.1.6.RELEASE |

|测试账号     |        |                                       |
|:-----------|:-------|:--------------------------------------|
|Username    |Password|Role & Authority                       |
|system_admin|123456  |ROLE_USER,ROLE_ADMIN,ROLE_SYSTEM_ADMIN |
|admin       |123456  |ROLE_USER,ROLE_ADMIN,/user/delete/{id} |
|user        |123456  |ROLE_USER                              |


表单登录、注销、后台管理、删除用户等。

主要配置文件：WebSecurityConfiguration.java
```
package com.gioov.springboot1springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/7 19:00
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private SessionRegistry sessionRegistry;

    @Bean
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }

    @Bean
    public SimpleUserDetailsService simpleUserDetailsService(){
        return new SimpleUserDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(simpleUserDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                  // 禁用 csrf
//                .csrf().disable()

                .authorizeRequests()

                // 匹配url，无需登录认证权限
                .antMatchers("/css/**","/js/**","img/**","/vendor/**").permitAll()

                .antMatchers("/","/index").permitAll()

                // 其它请求均需要认证
                .anyRequest().authenticated()

//                .antMatchers("/**").authenticated()

                .and()

                // 开启表单登录，设置登录 url
                .formLogin().loginPage("/login")

                // 自定义登录表单提交 url
                .loginProcessingUrl("/login/form")

                // 设置登录成功跳转到的 url
                .defaultSuccessUrl("/userCenter",true)

                // 设置登录失败跳转到的 url
                .failureUrl("/login?error").permitAll()

                .and()

                // 开启记住我功能， cookie 保存登录数据
                .rememberMe().rememberMeParameter("rememberMe")

                // 设置 cookie 有效期，7天内有效
                .tokenValiditySeconds(60 * 60 * 60 * 24 * 7)

                // 设置cookie的私钥
                .key("user")

                .and()

                .logout()

                // 设置注销 url
                .logoutUrl("/logout")

                //设置注销成功后跳转页面，默认是跳转到登录页面
                .logoutSuccessUrl("/login?logout").permitAll()

                .and()

                .sessionManagement()

                // 限制单个用户最大session，只允许一个用户登录
                .maximumSessions(1)


                // session 过期后跳转到登录 url
                .expiredUrl("/login")

                .sessionRegistry(sessionRegistry)

                .and()

                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)

                .and()


                // 无权限，访问指定 url
                .exceptionHandling().accessDeniedPage("/403");
    }


}

```

## Screenshots
![springboot1springsecurity_1.png](https://raw.githubusercontent.com/godcheese/springboot-example/master/springboot1springsecurity/screenshots/springboot1springsecurity_1.png)
![springboot1springsecurity_2.png](https://raw.githubusercontent.com/godcheese/springboot-example/master/springboot1springsecurity/screenshots/springboot1springsecurity_2.png)
![springboot1springsecurity_3.png](https://raw.githubusercontent.com/godcheese/springboot-example/master/springboot1springsecurity/screenshots/springboot1springsecurity_3.png)
![springboot1springsecurity_4.png](https://raw.githubusercontent.com/godcheese/springboot-example/master/springboot1springsecurity/screenshots/springboot1springsecurity_4.png)
