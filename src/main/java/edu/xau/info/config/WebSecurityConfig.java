package edu.xau.info.config;

import edu.xau.info.bean.Role;
import edu.xau.info.mapper.RoleMapper;
import edu.xau.info.service.impl.SpringDataUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;

/**
 * @Author: 杨斌
 * @Date: 2020/7/14 0014 18:09
 */
@Configuration
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    SpringDataUserDetailsService userDetailsService;
    @Autowired
    RoleMapper roleMapper;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        List<Role> roles = roleMapper.selectByExample(null);
        log.info("roles = {}", roles);
        for (Role role : roles) {
            http.authorizeRequests()
                    .antMatchers(role.getUrl() + "/**").hasAuthority(role.getName());
        }
        /*http.authorizeRequests()
                .antMatchers("/admin/**").hasAuthority("admin")
                .antMatchers("/student/**").hasAuthority("student")
                .antMatchers("/teacher/**").hasAuthority("teacher");*/

        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .successForwardUrl("/login-success")
                .and()
                .logout().logoutSuccessUrl("/");
    }
}
