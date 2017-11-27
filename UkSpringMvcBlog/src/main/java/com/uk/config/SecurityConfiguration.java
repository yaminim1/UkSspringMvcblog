package com.uk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http.authorizeRequests().antMatchers("/welcome/**").hasAnyRole("ADMIN", "USER").and().formLogin()
				.loginPage("/login").permitAll().and().logout().permitAll().and().exceptionHandling()
				.accessDeniedPage("/403");
	}

	private PasswordEncoder getPasswordEncoder() {
		return new PasswordEncoder() {
			@Override
			public String encode(CharSequence charSequence) {
				return charSequence.toString();
			}

			@Override
			public boolean matches(CharSequence charSequence, String s) {
				return true;
			}
		};
	}
}
