# example2

> Spring Security 实现身份验证和访问控制实例实例

- [官方实现](http://spring.io/guides/gs/securing-web/)

|测试账号     |        |                                       |
|:-----------|:-------|:--------------------------------------|
|Username    |Password|Role & Authority                       |
|system_admin|123456  |ROLE_USER,ROLE_ADMIN,ROLE_SYSTEM_ADMIN |
|admin       |123456  |ROLE_USER,ROLE_ADMIN,/user/delete/{id} |
|user        |123456  |ROLE_USER                              |

表单登录、注销、后台管理、删除用户等。

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

## Screenshots
![1.png](https://raw.githubusercontent.com/godcheese/spring-boot-example/master/spring-boot1/example2/screenshots/1.png)
![2.png](https://raw.githubusercontent.com/godcheese/spring-boot-example/master/spring-boot1/example2/screenshots/2.png)
![3.png](https://raw.githubusercontent.com/godcheese/spring-boot-example/master/spring-boot1/example2/screenshots/3.png)
![4.png](https://raw.githubusercontent.com/godcheese/spring-boot-example/master/spring-boot1/example2/screenshots/4.png)