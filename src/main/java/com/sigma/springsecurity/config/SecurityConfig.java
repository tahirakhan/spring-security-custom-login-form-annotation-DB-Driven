package com.sigma.springsecurity.config;


import com.sigma.springsecurity.config.core.CsrfHeaderFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  DataSource dataSource;

  @Autowired
  public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

    auth.jdbcAuthentication().dataSource(dataSource)
        .usersByUsernameQuery(
            "select username,password, enabled from users where username=?")
        .authoritiesByUsernameQuery(
            "select username, role from user_roles where username=?");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    //final HttpSessionCsrfTokenRepository tokenRepository = new HttpSessionCsrfTokenRepository();


    http.authorizeRequests()
        .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
        .antMatchers("/users/**").access("hasRole('ROLE_ADMIN')")
        //.antMatchers("/user/**").access("hasRole('ROLE_ADMIN')")
        .antMatchers("/user/**").permitAll()
        .antMatchers("/api/**").hasIpAddress("127.0.0.1")
        .antMatchers("/res/**").permitAll()
        .antMatchers("/bower_components/**").permitAll()
        .and()
        .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
        .formLogin().loginPage("/login").failureUrl("/login?error")
        .usernameParameter("username").passwordParameter("password")
        .and()
        .logout().logoutSuccessUrl("/login?logout")
        .and()
        .exceptionHandling().accessDeniedPage("/403")
        .and()
        .csrf().csrfTokenRepository(csrfTokenRepository());
  }
  private CsrfTokenRepository csrfTokenRepository() {
    HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
    repository.setHeaderName("X-XSRF-TOKEN");
    return repository;
  }
}