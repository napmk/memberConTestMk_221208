package com.napmkmk.home.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration

@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
	//보안 때문에 h2 접속안될때 넣어 줘야 함.
		http
        .authorizeRequests()
            .antMatchers("/","/**").access("permitAll")
            .antMatchers("/h2-console/**").permitAll() // 추가
        .and()
            .csrf() // 추가
            .ignoringAntMatchers("/h2-console/**").disable() // 추가
            .httpBasic();
		
	}
	public void configure(WebSecurity web)throws Exception{
        web.ignoring().antMatchers("/h2-console/**");
    }
	//보안 때문에 h2 접속안될때 넣어 줘야 함.
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}