package com.blog.app.one.securityConfig;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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
@AllArgsConstructor
public class SecurityConfig {
    private UserDetailsService userDetailsService;
    @Bean //Authentication Manager will use UserDetailService to get User from DB and it will also use PasswordEncoder to encode and decode the password automatically.
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
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
                        .permitAll()
                        .requestMatchers("/auth/**").permitAll().anyRequest().authenticated())
                        .httpBasic(Customizer.withDefaults()

                        );
        //Apart from Get requests we will authenticate all the requests
        return httpSecurity.build();
    }

    //As we are doing DB authentication, these inmemory objects are not required
   /* @Bean
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
    }*/
}
