package com.openclassrooms.poseidon.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
	
	 @Autowired
	    DataSource dataSource;

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.jdbcAuthentication()
	                .dataSource(dataSource)
	                .usersByUsernameQuery("select username , password, 'true' as enabled from users  where username=?")
	                .authoritiesByUsernameQuery("select username , role , 'true' as enabled from users  where username=?");
	    }

	    @Override
	    public void configure(HttpSecurity http) throws Exception {
	        http.csrf().disable()
	        .authorizeRequests().antMatchers("/").permitAll()
	                        .antMatchers("/bidList/**", "/rating/**", "/ruleName/**", "/trade/**", "/curvePoint/**").hasAnyAuthority("USER", "ADMIN","ROLE_USER")
	                        .antMatchers("/user/**").hasAuthority("ADMIN")
	                        .anyRequest().authenticated()
	                        .and()
	    	                .formLogin() .defaultSuccessUrl("/bidList/list")
	    	                .and()
	    	                .oauth2Login().defaultSuccessUrl("/bidList/list")
	    	                .and()
	    	                .logout().logoutUrl("/app-logout")
	    			                .clearAuthentication(true)
	    		                    .invalidateHttpSession(true)
	    		                    .deleteCookies("JSESSIONID")
	    		                    .logoutSuccessUrl("/")
	    					.and().exceptionHandling().accessDeniedPage("/app/error");
	    				            

	       
	            }

	    @Bean
	    public PasswordEncoder passwordEncoder () {
	        return new BCryptPasswordEncoder();
	    }
}
