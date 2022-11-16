package com.te.carmaintenance.securityconfig;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.te.carmaintenance.jwtrequest.JwtRequestFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService detailsService;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(detailsService);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable().cors();
			http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
		
//			.disable()
			http.authorizeRequests().antMatchers("/signup").permitAll()
								.antMatchers("/login").permitAll().antMatchers("/addcar").hasAnyRole("ADMIN","SUPERADMIN")
								.antMatchers("/deletecar").hasAnyRole("ADMIN","SUPERADMIN").antMatchers("/updatecar").hasAnyRole("ADMIN","SUPERADMIN")
								.antMatchers("/getallcars").hasAnyRole("ADMIN","SUPERADMIN").antMatchers("/search").hasAnyRole("ADMIN","USER")
								.antMatchers("/super/getallDetails").hasAnyRole("SUPERADMIN")
								.anyRequest().authenticated().and()
					
								.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();

	}

}
