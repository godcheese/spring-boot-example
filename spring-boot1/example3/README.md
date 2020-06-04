# example3

> Spring Security OAuth2 实现 OAuth 2.0 身份验证和访问控制实例

- [官方实现](http://spring.io/guides/tutorials/spring-boot-oauth2/)

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


## 技术栈

- Thymeleaf

  - 模板渲染 Web 页面

- NekoHTML

  - 支持 Thymeleaf 模板渲染支持 HTML 5 Web 页面

  - ```
    spring.thymeleaf.mode=LEGACYHTML5
    ```

- Spring Security

  - 支持身份验证和访问控制的框架

- MyBatis

  - 持久层

- MySQL

  - 数据库

- Spring Security OAuth2

  - 支持 OAuth 2.0 身份验证和访问控制的框架

  - ResourceServer（资源服务器）、AuthorizationServer（授权服务器）

## Screenshots
![1.png](https://raw.githubusercontent.com/godcheese/springboot-example/master/spring-boot1/example3/screenshots/1.png)