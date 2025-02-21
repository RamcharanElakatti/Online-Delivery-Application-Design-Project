package com.OnlineDeliveryApplication.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/vendor/**").hasRole("VENDOR")
            .antMatchers("/delivery/**").hasRole("DELIVERY_PERSONNEL")
            .antMatchers("/customer/**").hasRole("CUSTOMER")
            .antMatchers("/", "/register", "/login").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/home", true)
            .permitAll()
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login")
            .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
            .and()
            .withUser("vendor").password(passwordEncoder().encode("vendor")).roles("VENDOR")
            .and()
            .withUser("delivery").password(passwordEncoder().encode("delivery")).roles("DELIVERY_PERSONNEL")
            .and()
            .withUser("customer").password(passwordEncoder().encode("customer")).roles("CUSTOMER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}