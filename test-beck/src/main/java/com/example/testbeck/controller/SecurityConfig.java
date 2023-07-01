package com.example.testbeck.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        LogoutConfigurer<HttpSecurity> httpSecurityLogoutConfigurer = http
                .authorizeRequests()
                .dispatcherTypeMatchers(HttpMethod.valueOf("/public/**")).permitAll() // Публичные ресурсы
                .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
                .and()
                .formLogin() // Форма входа
                .loginPage("/") // URL страницы входа
                .permitAll()
                .and()
                .logout() // Выход
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication() // Аутентификация в памяти
                .withUser("user").password("{noop}password").roles("USER"); // Логин, пароль и роль пользователя
    }
}
