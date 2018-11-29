/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.proyectoCAFD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Bruno Conte
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.authorizeRequests().antMatchers("/","/fonts/**","/css/**","/js/**","/home/**","/img/**",
                "/uploadsE/**","/uploadsC/**","/uploads/**","/ClasesHome/**","/EventosHome/**","/inscripcionClases/**").permitAll()
                .antMatchers("/alumnos/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .permitAll()
                .and()
                .logout().permitAll();
    }
    
    
    @Autowired
    public void configuredGlobal(AuthenticationManagerBuilder build) throws Exception
    {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserBuilder users = User.builder().passwordEncoder(encoder::encode);
        
        build.inMemoryAuthentication().withUser(users.username("admin").password("12345").roles("ADMIN"));
                
    }
}
