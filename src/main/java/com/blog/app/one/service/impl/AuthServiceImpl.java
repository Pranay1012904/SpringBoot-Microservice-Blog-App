package com.blog.app.one.service.impl;

import com.blog.app.one.dto.LoginDto;
import com.blog.app.one.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    @Override
    public String LoginService(LoginDto loginDto) {
     Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            loginDto.getUserNameOrEmail(), loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "User Logged In Successfully!";
    }
}
