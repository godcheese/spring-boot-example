
# [springboot1springsecurityoauth2](https://github.com/godcheese/springboot-example/tree/master/springboot1springsecurityoauth2)
> Spring Boot 1.x 整合 Spring Security OAuth2 实现角色/权限控制实例

- [官方实现](http://spring.io/guides/tutorials/spring-boot-oauth2/)

|环境  |版本|
|:-----|---|
|Java  |1.8|
|MySQL |5.7|
|Maven |3.5|
|Tomcat|8.5|

|依赖            |  版本        |
|:---------------------|:-------------|
|Spring Boot           |1.5.11.RELEASE|
|Spring Security       |4.2.5.RELEASE |
|Spring Security OAuth2|2.0.14.RELEASE|
|MyBatis               |3.4.6         |
|Thymeleaf             |2.1.6.RELEASE |

|测试账号     |        |                                       |
|:-----------|:-------|:--------------------------------------|
|Username    |Password|Role & Authority                       |
|system_admin|123456  |ROLE_USER,ROLE_ADMIN,ROLE_SYSTEM_ADMIN |
|admin       |123456  |ROLE_USER,ROLE_ADMIN,/user/delete/{id} |
|user        |123456  |ROLE_USER                              |


### 获取 token
http://localhost:8080/oauth/token?grant_type=password&scope=all&client_id=1&client_secret=123456&username=system_admin&password=123456
### 刷新 token
http://localhost:8080/oauth/token?grant_type=refresh_token&scope=all&client_id=1&client_secret=123456&refresh_token95f48562-bec0-4b1c-8438-b25e3d9e6085

### 客户端获取 token
http://localhost:8080/oauth/token?grant_type=client_credentials&scope=all&client_id=1&client_secret=123456



主要配置文件：WebSecurityConfiguration.java
```
/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/9 15:29
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Autowired
    SessionRegistry sessionRegistry;

    @Bean
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }

    @Autowired
    private DataSource dataSource;

    @Bean
    public ClientDetailsService clientDetailsService(){
        return new JdbcClientDetailsService(dataSource);
//		return new InMemoryClientDetailsService(); InMemoryClient
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }
    

    @Bean
    public SimpleUserDetailsService simpleUserDetailsService(){
        return new SimpleUserDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 指定自定义的 UserDetailService 必需不然 refresh_token 会报 UserDetailsService is required.
        auth.userDetailsService(simpleUserDetailsService())
                
                // 指定 user password 加密方式
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                // 禁用 csrf
//                .csrf().disable()

                // 禁用 ROLE_ANONYMOUS 角色
//                .anonymous().disable()

                .authorizeRequests()

                // 静态资源 url ，无需登录认证权限
                .antMatchers("/css/**","/js/**","img/**", "/static/vendor/**").permitAll()

                // 指定 url ，无需登录认证权限
                .antMatchers("/oauth/**","/web/**").permitAll()
//                .antMatchers("/user/**").hasAnyRole("USER","ADMIN")
                .anyRequest().authenticated();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Autowired
    public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore){
        TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
        handler.setTokenStore(tokenStore);
        handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService()));
        handler.setClientDetailsService(clientDetailsService());
        return handler;
    }

    @Bean
    @Autowired
    public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
        TokenApprovalStore store = new TokenApprovalStore();
        store.setTokenStore(tokenStore);
        return store;
    }
}


```

## Screenshots
![springboot1springsecurityoauth2_1.png](https://raw.githubusercontent.com/godcheese/springboot-example/master/springboot1springsecurityoauth2/screenshots/springboot1springsecurityoauth2_1.png)