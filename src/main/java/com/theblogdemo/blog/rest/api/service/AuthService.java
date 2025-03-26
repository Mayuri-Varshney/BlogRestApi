package com.theblogdemo.blog.rest.api.service;

import com.theblogdemo.blog.rest.api.payload.LoginDto;
import com.theblogdemo.blog.rest.api.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);

}
