package com.krzysztof.app.SecurityConfig;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceClass userDetailsServiceClass;

    /**
     * przeciążenie metody configure() z parametrami
     * @param http zapytanie http
     * @throws Exception przechwytuje wyjątek
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
                http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/h2", "/admin/**", "/admin").hasAnyRole("ADMIN")
                .antMatchers("/api/**").hasAnyRole("ADMIN", "USER")
                .and().formLogin().defaultSuccessUrl("/api/view-questionnaire").permitAll()
                .and().csrf().disable().logout().permitAll()
                .and().headers().frameOptions().disable();
    }

    /**
     * przeciążenie metody configure()
     * @param web przekazuje parametr WebSecurity
     */
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


    /**
     * przeciążenie metody configure()
     * @param auth menager autentykacji
     * @throws Exception przsechwytuje wyjątek
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceClass);
    }

}
