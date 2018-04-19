package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    UserDetailsService customUserService() {
        return new CustomUserService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService())
                .passwordEncoder(new MyPasswordEncoder())//在此处应用自定义PasswordEncoder
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                //.antMatchers("/resources/**", "/signup", "/about").permitAll()

                .antMatchers("/type/delete").hasRole("ADMIN")
                .antMatchers("/type/add").hasRole("ADMIN")
                .antMatchers("/sevProject/delete").hasRole("ADMIN")
                .antMatchers("/sevProject/add").hasRole("ADMIN")
                .antMatchers("/type/edit").hasRole("ADMIN")
                .antMatchers("/sevProject/edit").hasRole("ADMIN")
                //.antMatchers("/db/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/login","/javascripts/**","/images/**","/lib/**","/stylesheets/**","/session/invalid")
                .permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login")
                //设置默认登录成功跳转页面
                .defaultSuccessUrl("/index").failureUrl("/login?error").permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll();

        http.csrf().disable();
    }
}
