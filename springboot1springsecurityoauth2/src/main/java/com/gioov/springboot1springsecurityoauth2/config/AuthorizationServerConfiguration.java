package com.gioov.springboot1springsecurityoauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/9 15:29
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {


    private static final String REALM="MY_OAUTH_REALM";

    @Autowired
    private UserApprovalHandler userApprovalHandler;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    DataSource dataSource;

    @Autowired
    public TokenStore tokenStore(){
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    public ClientDetailsService clientDetailsService(){
        return new JdbcClientDetailsService(dataSource);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        // 内存中的客户端
//		clients.inMemory()
//				.withClient("client2")//客户端ID
//				.authorizedGrantTypes("password", "authorization_code","client_credentials", "refresh_token", "implicit").redirectUris("http://localhost:8080")
//				.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
//				.scopes("read", "write", "trust")//授权用户的操作权限
//				.secret("123456");//密码

        clients.withClientDetails(clientDetailsService());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore()).userApprovalHandler(userApprovalHandler)
                .authenticationManager(authenticationManager);

        // 生效自定义 Token service
        endpoints.tokenServices(tokenServices());
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();

        // support refresh token
        tokenServices.setSupportRefreshToken(true);

        // use jdbc token store
        tokenServices.setTokenStore(tokenStore());

        // 1天 (int) TimeUnit.DAYS.toSeconds(1)
        // access token有效期为120秒
        tokenServices.setAccessTokenValiditySeconds(120);

        // refresh token有效期为180秒
        tokenServices.setRefreshTokenValiditySeconds(180);
        return tokenServices;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

//        security.realm(REALM);

        // 允许表单认证
        security.allowFormAuthenticationForClients();

    }

}
