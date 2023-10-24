package com.example.practicetogheter.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import com.example.practicetogheter.services.SpringDataUserDetailsService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig{


    @Autowired
    DataSource dataSource;

    //password code/decdode
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringDataUserDetailsService customUserDetailsService() {
        return new SpringDataUserDetailsService();
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasAnyRole("DRIVER")
                .antMatchers("/admin/**").hasAnyRole("USER")
                .anyRequest().permitAll()
                .and().formLogin()
             .loginPage("/login")
                .defaultSuccessUrl("/welcome")
                .and().logout().logoutSuccessUrl("/goodbye")
                .permitAll()
                .and().exceptionHandling().accessDeniedPage("/403");
        return http.build();
    }

    //    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
      //  registry.addViewController("/login").setViewName("admin/login");
        registry.addViewController("/403").setViewName("403");
    }


}
