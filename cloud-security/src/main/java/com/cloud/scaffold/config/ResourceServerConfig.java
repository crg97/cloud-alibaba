package com.cloud.scaffold.config;

import com.cloud.scaffold.handle.SecurityAccessDeniedHandler;
import com.cloud.scaffold.handle.SecurityAuthenticationEntryPoint;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Resource
    protected RemoteTokenServices remoteTokenServices;
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private SecurityAccessDeniedHandler securityAccessDeniedHandler;
    @Resource
    private SecurityAuthenticationEntryPoint securityAuthenticationEntryPoint;

    /**
     * 默认注入了一个
     * @param resources
     * @throws Exception
     */
//    @Bean
//    public RemoteTokenServices tokenService() {
//        RemoteTokenServices tokenService = new RemoteTokenServices();
//        tokenService.setRestTemplate(restTemplate());
//        tokenService.setClientId("provider_1");
//        tokenService.setClientSecret("secret");
//        tokenService.setCheckTokenEndpointUrl(checkTokenEndpointUrl);
//        return tokenService;
//    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        remoteTokenServices.setRestTemplate(restTemplate);
        remoteTokenServices.setClientId("provider_1");
        remoteTokenServices.setClientSecret("secret");
        resources.tokenServices(remoteTokenServices);
        resources.authenticationEntryPoint(securityAuthenticationEntryPoint);
        resources.accessDeniedHandler(securityAccessDeniedHandler);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
            .authorizeRequests()
//            .antMatchers("/test/**").hasAuthority("admin")
            .anyRequest().authenticated()
            .and()
            // 不需要session
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .csrf().disable();
    }
}