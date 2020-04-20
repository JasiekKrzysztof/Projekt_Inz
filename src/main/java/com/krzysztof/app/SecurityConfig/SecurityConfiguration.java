package com.krzysztof.app.SecurityConfig;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//    private static final String LOGIN_PROCESSING_URL = "/login";
//    private static final String LOGIN_FAILURE_URL = "/login";
//    private static final String LOGIN_URL = "/login";
//    private static final String LOGOUT_SUCCESS_URL = "/login";

    @Autowired
    UserDetailsServiceClass userDetailsServiceClass;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and().authorizeRequests()
                .antMatchers("/").permitAll()
                //.antMatchers("Registration").permitAll()
                .antMatchers("/test").permitAll()
                .antMatchers("/h2").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("ADMIN", "USER")
                //.antMatchers("/api/**").hasAnyRole("ADMIN", "USER")
                .and().formLogin().permitAll()
                .and().csrf().disable().logout().permitAll()
                .and().headers().frameOptions().disable();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                // Vaadin Flow static resources
                "/VAADIN/**",

                // the standard favicon URI
                "/favicon.ico",

                // the robots exclusion standard
                "/robots.txt",

                // web application manifest
                "/manifest.webmanifest",
                "/sw.js",
                "/offline-page.html",

                // icons and images
                "/icons/**",
                "/images/**",

                // (development mode) static resources
                "/frontend/**",

                // (development mode) webjars
                "/webjars/**",

                // (development mode) H2 debugging console
                "/h2-console/**",

                // (production mode) static resources
                "/frontend-es5/**", "/frontend-es6/**");
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceClass);
    }

}
