package com.blog.app.one.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderUtil {

    public static void main(String[] args){
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        System.out.print("\n"+passwordEncoder.encode("pranay@123"));
        System.out.print("\n"+passwordEncoder.encode("ronny@123"));
    }
}
