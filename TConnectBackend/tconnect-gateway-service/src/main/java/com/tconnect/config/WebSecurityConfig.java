package com.tconnect.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tconnect.security.JWTAuthenticationEntryPoint;
import com.tconnect.security.JwtTokenProvider;
import com.tconnect.security.LoginUserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private LoginUserService loginUserService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private JWTAuthenticationEntryPoint jWTAuthenticationEntryPoint;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginUserService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors();
		http.authorizeRequests()
			.antMatchers("/", "/login","/login/**", "/user/register/student", "/user/register/agent",
					"/list/university", "/password/**", "/user/images/**", "/org/images/**", 
					"/user/manage/agent", "/org/company/profile", "/testdb").permitAll()
			.antMatchers(HttpMethod.OPTIONS).permitAll()
			.antMatchers("/user/profile/**", "/org/profile/**", "/feed/**",
					"/list/company", "/user/explore", "/user/view/company").hasAnyAuthority("agent", "student")
			.anyRequest().authenticated()
			.and().logout().permitAll()
			.and().exceptionHandling().authenticationEntryPoint(jWTAuthenticationEntryPoint)
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().httpBasic();
		http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/user/images/**");
	}
}
