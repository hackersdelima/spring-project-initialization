package com.digi.app.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*
POWERED BY PEEPALSOFT - SHISHIR KARKI
 */
@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService myUserDetailService;

    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(myUserDetailService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] adminPathsArray = new String[]{"/users/**", "/staffs/**", "/office/**"};
        String[] authorizerPathsArray = new String[]{"/authorize/**"};
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/resources/assets/externalJs/**").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/login*").permitAll()
                .antMatchers(adminPathsArray).hasAuthority("ADMIN")
                .antMatchers(authorizerPathsArray).hasAuthority("AUTHORIZER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login-page")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/dashboard", true)
                .failureUrl("/login.html?error=true")
                // .failureHandler(authenticationFailureHandler())
                .and()
                .logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID");
        // .logoutSuccessHandler(logoutSuccessHandler());
    }

    //defining a bean to encrypt passwords
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
