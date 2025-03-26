package com.demoblogapi.demo.blog.api.service;

import com.demoblogapi.demo.blog.api.payload.LoginDto;
import com.demoblogapi.demo.blog.api.payload.RegisterDto;
import com.demoblogapi.demo.blog.api.payload.LoginDto;
import com.demoblogapi.demo.blog.api.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);

}
