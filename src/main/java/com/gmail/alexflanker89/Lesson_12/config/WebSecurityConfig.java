package com.gmail.alexflanker89.Lesson_12.config;


import com.gmail.alexflanker89.Lesson_12.domain.auth.Role;
import com.gmail.alexflanker89.Lesson_12.services.UserService;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers(HttpMethod.GET,"/","/authors","/book","/books**","/genres", "/js/**", "/error**").permitAll()
                        .and()
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST,"/registration").permitAll()
                        .and()
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST,"/","/authors","/book","/books**","/genres").hasAuthority("ADMIN")
                        .and()
                .authorizeRequests()
                    .antMatchers(HttpMethod.DELETE,"/authors","/book","/books**","/genres","/comment/**").hasAuthority("ADMIN")
                        .and()
                .authorizeRequests().antMatchers(HttpMethod.POST,"/login").permitAll()
                        .and()
                    .logout().logoutSuccessUrl("/").permitAll()
                        .and()
                            .csrf()
                                .disable();

        http.formLogin().successHandler(
                (httpServletRequest, httpServletResponse, authentication) -> httpServletResponse.setStatus(200))
                .and()
            .anonymous().principal(User.builder()
                .username("anonymouse")
                .password("anon")
                .roles(Role.ANONYMOUSE.getAuthority()));




    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }
}
