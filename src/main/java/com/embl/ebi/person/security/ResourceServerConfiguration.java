package com.embl.ebi.person.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    private static final String RESOURCE_ID = "person-rest-api";
//
//
//    private static final String[] AUTH_WHITELIST = {
//            // -- swagger ui urls
//            "/v2/api-docs",
//            "/swagger-resources",
//            "/swagger-resources/**",
//            "/configuration/ui",
//            "/configuration/security",
//            "/swagger-ui.html",
//            "/webjars/**"
//            // other public endpoints of your API may be appended to this array
//    };
//
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) {
//        resources.resourceId(RESOURCE_ID);
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.requestMatchers()
//                .antMatchers().and().authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/api/v1/user/**").permitAll()
//                .antMatchers(AUTH_WHITELIST).permitAll()
//                .antMatchers("/oauth/token").permitAll()
//                .antMatchers("/oauth/token/**").permitAll()
//                .antMatchers( "/api/v1/user").permitAll()
//                .antMatchers( "/api/v1/user/**").permitAll()
//
////                .antMatchers(HttpMethod.POST, SECURED_PATTERN).access(SECURED_WRITE_SCOPE)
//                .anyRequest().authenticated();
//    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.
                anonymous().disable()
                .requestMatchers().antMatchers("/api/v1/person/**")
                .and().authorizeRequests()
                .antMatchers("/api/v1/user").permitAll()
                .antMatchers("/api/v1/user/**").permitAll()
                .antMatchers("/api/v1/person/**").access("hasRole('ROLE_admin')")
                .antMatchers("/api/v1/person").access("hasRole('ROLE_admin')")
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }


}