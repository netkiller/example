// package cn.netkiller.oauth2.config;
//
// import java.util.HashMap;
// import java.util.Map;
// import java.util.concurrent.TimeUnit;
//
// import javax.annotation.Resource;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.oauth2.common.OAuth2AccessToken;
// import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
// import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
// import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
// import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
// import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
// import org.springframework.security.oauth2.provider.OAuth2Authentication;
// import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
// import org.springframework.security.oauth2.provider.token.TokenStore;
// import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
// import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//
/// ***
// * 身份授权认证服务配置 配置客户端、token存储方式等
// */
//
// @Configuration
// @EnableAuthorizationServer // 注解开启验证服务器 提供/oauth/authorize,/oauth/token,/oauth/check_token,/oauth/confirm_access,/oauth/error
// public class AuthorizationServerConfiguration extends ConfigurerAdapter {
//
// public AuthorizationServerConfiguration() {
// // TODO Auto-generated constructor stub
// }
//
// private static final String REDIRECT_URL = "http://www.netkiller.cn/";
// private static final String CLIEN_ID_THREE = "client_3"; // 客户端3
// private static final String CLIENT_SECRET = "secret"; // secret客户端安全码
// private static final String RESOURCE_ID = "resource_id"; // 指定哪些资源是需要授权验证的
// // private static final String GRANT_TYPE_PASSWORD = "password"; // 密码模式授权模式
// // private static final String AUTHORIZATION_CODE = "authorization_code"; // 授权码模式 授权码模式使用到了回调地址，是最为复杂的方式，通常网站中经常出现的微博，qq第三方登录，都会采用这个形式。
// // private static final String REFRESH_TOKEN = "refresh_token"; //
// // private static final String IMPLICIT = "implicit"; // 简化授权模式
// // private static final String GRANT_TYPE = "client_credentials"; // 客户端模式
//
// private static final String SCOPE_READ = "read";
// private static final String SCOPE_WRITE = "write";
// private static final String TRUST = "trust";
//
// private static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1 * 60 * 60; //
// private static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6 * 60 * 60; //
//
//// @Autowired
//// private AuthenticationManager authenticationManager; // 认证方式
//// @Resource(name = "userService")
//// private UserDetailsService userDetailsService;
//
// @Override
// public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
// String secret = new BCryptPasswordEncoder().encode(CLIENT_SECRET); // 用 BCrypt 对密码编码
// // 配置3个个客户端,一个用于password认证、一个用于client认证、一个用于authorization_code认证
// configurer.inMemory() // 使用in-memory存储
// .withClient(CLIEN_ID_THREE) // client_id用来标识客户的Id 客户端3
// .secret(secret) // secret客户端安全码
// .resourceIds(RESOURCE_ID) // 资源ID
// .authorizedGrantTypes("authorization_code", "client_credentials", "refresh_token", "password", "implicit") // 允许授权类型
// .scopes(SCOPE_READ, SCOPE_WRITE, TRUST) // 允许授权范围
// .authorities("ROLE_CLIENT") // 客户端可以使用的权限
// // .redirectUris(REDIRECT_URL) //指定可以接受令牌和授权码的重定向URIs
// .autoApprove(true) // 为true 则不会被重定向到授权的页面，也不需要手动给请求授权,直接自动授权成功返回code
// .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS) // token 时间秒
// .refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS);// 刷新token 时间 秒
//
// }
//
// // @Override
// // public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
// // endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager).accessTokenConverter(accessTokenConverter()).userDetailsService(userDetailsService) // 必须注入userDetailsService否则根据refresh_token无法加载用户信息
// // .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST, HttpMethod.OPTIONS) // 支持GET POST 请求获取token
// // .reuseRefreshTokens(true) // 开启刷新token
// // .tokenServices(tokenServices());
// //
// // }
//
// /**
// * 认证服务器的安全配置
// *
// * @param security
// * @throws Exception
// */
// @Override
// public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
// security.realm(RESOURCE_ID).tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()") // isAuthenticated():排除anonymous isFullyAuthenticated():排除anonymous以及remember-me
// .allowFormAuthenticationForClients(); // 允许表单认证 这段代码在授权码模式下会导致无法根据code 获取token
// }
//
// // @Bean
// // public JwtAccessTokenConverter accessTokenConverter() {
// // JwtAccessTokenConverter converter = new JwtAccessTokenConverter() {
// // /**
// // * 自定义一些token返回的信息
// // *
// // * @param accessToken
// // * @param authentication
// // * @return
// // */
// // @Override
// // public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
// // String grantType = authentication.getOAuth2Request().getGrantType();
// // // 只有如下两种模式才能获取到当前用户信息
// // if ("authorization_code".equals(grantType) || "password".equals(grantType)) {
// // String userName = authentication.getUserAuthentication().getName();
// // // 自定义一些token 信息 会在获取token返回结果中展示出来
// // final Map<String, Object> additionalInformation = new HashMap<>();
// // additionalInformation.put("user_name", userName);
// // ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
// // }
// // OAuth2AccessToken token = super.enhance(accessToken, authentication);
// // return token;
// // }
// // };
// // converter.setSigningKey("bcrypt");
// // return converter;
// // }
// //
// // @Bean
// // public TokenStore tokenStore() {
// // // 基于jwt实现令牌（Access Token）
// // return new JwtTokenStore(accessTokenConverter());
// // }
// //
// // /**
// // * 重写默认的资源服务token
// // *
// // * @return
// // */
// // @Bean
// // public DefaultTokenServices tokenServices() {
// // final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
// // defaultTokenServices.setTokenEnhancer(accessTokenConverter());
// // defaultTokenServices.setTokenStore(tokenStore());
// // defaultTokenServices.setSupportRefreshToken(true);
// // defaultTokenServices.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30)); // 30天
// // return defaultTokenServices;
// // }
//
// }
