package com.feriantes.portafolio.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class conf
extends WebSecurityConfigurerAdapter {
     @Autowired
     UsuarioSesionServicio userDetailsService;
      
     protected void configure(HttpSecurity http) throws Exception {
         
         
         http
            .headers().frameOptions().disable();
         http
             .authorizeRequests()
                 .antMatchers("/").permitAll()
                 .antMatchers("/login").permitAll()
                 .anyRequest().authenticated()
                 .and()
             .csrf().disable()
             .formLogin()
                 .loginPage("/login")
                 .defaultSuccessUrl("/home")
                 .failureUrl("/login?error=true")
                 .usernameParameter("email")
                 .passwordParameter("password")
                 .and()
             .logout()
                 .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).invalidateHttpSession(true).logoutSuccessUrl("/login").and()
                 .exceptionHandling().accessDeniedPage("/access-denied")
                 ;
     }
 
     @Bean
     public PasswordEncoder passwordEncoder() {
         return new BCryptPasswordEncoder();
     }
 
     @Override
     public void configure(WebSecurity web) throws Exception {
         web.ignoring().antMatchers("/resources/*", "/static/*", "/css/*", "/js/*", "/img/*");
     }
 
     @Override
     public void configure(AuthenticationManagerBuilder builder) throws Exception {
         builder.userDetailsService(userDetailsService);
     }

 }
