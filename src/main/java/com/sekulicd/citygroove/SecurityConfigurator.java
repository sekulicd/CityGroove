package com.sekulicd.citygroove;

import com.sekulicd.citygroove.core.security.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfigurator extends WebSecurityConfigurerAdapter {

    private final JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        authorizeRequests(httpSecurity);
        withoutCookie(httpSecurity);
        csrf(httpSecurity);
        addJwtAuthFilter(httpSecurity);
    }

    private void withoutCookie(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    private void csrf(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
    }

    private void authorizeRequests(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().
                antMatchers("/public/**").permitAll().
                anyRequest().authenticated();
    }

    private void addJwtAuthFilter(HttpSecurity httpSecurity){
        httpSecurity.addFilterAfter(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
