package com.demoblogapi.demo.blog.api.controller;
import com.demoblogapi.demo.blog.api.payload.LoginDto;
import com.demoblogapi.demo.blog.api.payload.RegisterDto;
import com.demoblogapi.demo.blog.api.service.AuthService;
import com.demoblogapi.demo.blog.api.payload.LoginDto;
import com.demoblogapi.demo.blog.api.payload.RegisterDto;
import com.demoblogapi.demo.blog.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String message = authService.register(registerDto);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        String message = authService.login(loginDto);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
