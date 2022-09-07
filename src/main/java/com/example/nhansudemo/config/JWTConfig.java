package com.example.nhansudemo.config;

import com.example.nhansudemo.security.JwtFilter;
import com.example.nhansudemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
/*
 * Phan Duy Thái
 * 9/7/2022
 */
//Config phan quyen
public class JWTConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtFilter jwtFilter;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {//thiet lap co che xac thuc
        auth.userDetailsService(employeeService);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();//mã hóa code
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    //
    @Override
    protected void configure(HttpSecurity http) throws Exception{//Phan quyen nguoi dung
        http.csrf().disable().authorizeRequests().antMatchers("/authenticate").permitAll()
                .antMatchers(HttpMethod.GET).permitAll()

                .antMatchers(HttpMethod.DELETE,"delete/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST).hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT).hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and().exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
