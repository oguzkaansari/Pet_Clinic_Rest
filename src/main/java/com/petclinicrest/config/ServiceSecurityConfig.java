package com.petclinicrest.config;

import com.petclinicrest.services.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ServiceSecurityConfig extends WebSecurityConfigurerAdapter {

    final UserService uService;
    public ServiceSecurityConfig(UserService uService) {
        this.uService = uService;
    }

    private static final String[] SWAGGER_WHITELIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
    };

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(uService).passwordEncoder(uService.encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/static/**").hasAnyRole("ADMIN","DOCTOR", "SECRETARY")
                .antMatchers("/customer/**").hasAnyRole("ADMIN","DOCTOR", "SECRETARY")
                .antMatchers("/category/**").hasAnyRole("ADMIN", "SECRETARY")
                .antMatchers("/customer_group/**").hasAnyRole("ADMIN", "SECRETARY")
                .antMatchers("/calendar/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY")
                .antMatchers("/calendar/admin_secretary/**").hasAnyRole("ADMIN", "SECRETARY")
                .antMatchers("/sale/**").hasAnyRole("ADMIN","DOCTOR", "SECRETARY")
                .antMatchers("/sale/add").hasAnyRole("DOCTOR")
                .antMatchers("/statistics/**").hasAnyRole("ADMIN","DOCTOR", "SECRETARY")
                .antMatchers("/ticket/**").hasAnyRole("ADMIN","SECRETARY")
                .antMatchers("/receipt/**").hasAnyRole("ADMIN","SECRETARY")
                .antMatchers("/test/**").hasAnyRole("ADMIN","DOCTOR")
                .antMatchers("/product/**").hasAnyRole("ADMIN","SECRETARY")
                .antMatchers("/supplier/**").hasAnyRole("ADMIN","SECRETARY")
                .antMatchers("/agenda/**").hasAnyRole("ADMIN","SECRETARY", "DOCTOR")
                .antMatchers("/admin/**").hasRole(("ADMIN"))
                .antMatchers("/user/**").hasAnyRole("ADMIN","SECRETARY", "DOCTOR")
                .antMatchers("/swagger-ui/#").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers(SWAGGER_WHITELIST).permitAll()
                .and()
                .csrf().disable()
                .formLogin().disable()
                .logout().logoutUrl("/logout").invalidateHttpSession(true) ;

    }

}
