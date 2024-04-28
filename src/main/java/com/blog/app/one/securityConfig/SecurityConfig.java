package com.blog.app.one.securityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();//uses BCrypt algo to encode the password
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        //csrf->Cross-Site Request Forgery
        httpSecurity.csrf(csrf-> csrf.disable()).authorizeHttpRequests(authorize-> /*authorize.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());*/
                authorize.requestMatchers(HttpMethod.GET,"/post/api/**")
                        .permitAll().anyRequest().authenticated())
                        .httpBasic(Customizer.withDefaults());
        //Apart from Get requests we will authenticate all the requests
        return httpSecurity.build();
    }
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails pranay= User.builder()
        .username("Pranay")
                .password(passwordEncoder().encode("Pranay@123"))
                .roles("USER").build();

        UserDetails admin=User.builder()
                .username("Admin")
                .password(passwordEncoder().encode("Admin@123"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(pranay,admin);
    }
}
