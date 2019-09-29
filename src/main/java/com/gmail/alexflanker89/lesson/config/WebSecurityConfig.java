package com.gmail.alexflanker89.lesson.config;


import com.gmail.alexflanker89.lesson.services.UserServiceDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserServiceDetails userServiceDetails;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                .and()
                .authorizeRequests().antMatchers("/actuator/**").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/", "/authors", "/book", "/books**", "/genres", "/js/**", "/error**").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/registration").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/", "/authors", "/book", "/books**", "/genres").hasAuthority("ADMIN")
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/authors", "/book", "/books**", "/genres", "/comment/**").hasAuthority("ADMIN")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/login").permitAll()
                .and()
                .logout().logoutSuccessUrl("/").permitAll()
                .and()
                .csrf()
                .disable().formLogin().successHandler(
                (httpServletRequest, httpServletResponse, authentication) -> httpServletResponse.setStatus(200));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceDetails)
                .passwordEncoder(getPasswordEncoder());
    }

    @Bean(name ="sessionRegistry")
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
}
