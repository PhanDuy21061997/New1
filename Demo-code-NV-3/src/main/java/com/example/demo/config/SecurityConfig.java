package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.example.demo.rest.CustomAccessDeniedHandler;
import com.example.demo.rest.JwtAuthenticationTokenFilter;
import com.example.demo.rest.RestAuthenticationEntryPoint;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	  public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
	    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
	    jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
	    return jwtAuthenticationTokenFilter;
	  }
	  @Bean
	  public RestAuthenticationEntryPoint restServicesEntryPoint() {
	    return new RestAuthenticationEntryPoint();
	  }
	  @Bean
	  public CustomAccessDeniedHandler customAccessDeniedHandler() {
	    return new CustomAccessDeniedHandler();
	  }
	  @Bean
	  @Override
	  protected AuthenticationManager authenticationManager() throws Exception {
	    return super.authenticationManager();
	  }
	  protected void configure(HttpSecurity http) throws Exception {
	    // Disable crsf cho đường dẫn /rest/**
	    http.csrf().ignoringAntMatchers("/api/**");
	    http.authorizeRequests().antMatchers("/api/csv**").permitAll();
	    http.authorizeRequests().antMatchers("/api/user/loginAPI**").permitAll();
	    http.antMatcher("/api/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
	     //   .antMatchers(HttpMethod.GET,"/api/user/information/{id}").access("hasRole('ROLE_USER')")//("hasRole('ROLE_ADMIN) or #authUser.id == #userId")
	        .antMatchers(HttpMethod.GET, "/api/admin**").access("hasRole('ROLE_ADMIN') ")//or hasRole('ROLE_USER')//@PreAuthorize("hasRole('ADMIN') or principal.userId == #id")
	        .antMatchers(HttpMethod.POST, "/api/admin**").access("hasRole('ROLE_ADMIN')")
	        .antMatchers(HttpMethod.PUT, "/api/admin**").access("hasRole('ROLE_ADMIN')")
	        .antMatchers(HttpMethod.GET, "/api/personel/**").access("hasRole('ROLE_USER') ||(principal.id == #id) ")//or hasRole('ROLE_USER')//@PreAuthorize("hasRole('ADMIN') or principal.userId == #id")
	        .antMatchers(HttpMethod.POST, "/api/personel/**").access("hasRole('ROLE_USER')")
	        .antMatchers(HttpMethod.DELETE, "/api/admin/**").access("hasRole('ROLE_ADMIN')").and()//phan quye
	       // .anyRequest().authenticated().and()
	        .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
	        .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
	  }
	 
}
