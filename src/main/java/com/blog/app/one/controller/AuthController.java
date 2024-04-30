package com.blog.app.one.controller;

import com.blog.app.one.dto.LoginDto;
import com.blog.app.one.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;

    //Build Login Rest Api / Endpoint
    @PostMapping(value={"/login","/signin"})
    public ResponseEntity<String> loginEndPoint(@RequestBody LoginDto loginDto){
       String response= authService.LoginService(loginDto);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
