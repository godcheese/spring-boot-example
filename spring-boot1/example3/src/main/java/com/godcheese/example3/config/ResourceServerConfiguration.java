package com.godcheese.example3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/9 15:29
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "demo_res_api";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
//                .csrf().disable()
                .anonymous().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/**")
                .access("#oauth2.hasScope('read') OR #oauth2.hasScope('all')")
                .antMatchers(HttpMethod.POST, "/api/**")
                .access("#oauth2.hasScope('write') OR #oauth2.hasScope('all')")
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());

    }
}
