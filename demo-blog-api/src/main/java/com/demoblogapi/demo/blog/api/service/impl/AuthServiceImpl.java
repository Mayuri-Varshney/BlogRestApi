package com.demoblogapi.demo.blog.api.service.impl;

import com.demoblogapi.demo.blog.api.repository.RoleRepository;
import com.demoblogapi.demo.blog.api.repository.UserRepository;
import com.demoblogapi.demo.blog.api.service.AuthService;
import com.demoblogapi.demo.blog.api.entity.Role;
import com.demoblogapi.demo.blog.api.entity.User;
import com.demoblogapi.demo.blog.api.payload.LoginDto;
import com.demoblogapi.demo.blog.api.payload.RegisterDto;
import com.demoblogapi.demo.blog.api.repository.RoleRepository;
import com.demoblogapi.demo.blog.api.repository.UserRepository;
import com.demoblogapi.demo.blog.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "User logged in successfully!";
    }

    @Override
    public String register(RegisterDto registerDto) {

        if(userRepository.existsByUsername(registerDto.getUsername())){
            return "Username is already taken!";
        }


        if(userRepository.existsByEmail(registerDto.getEmail())){
            return "Email is already taken!";
        }


        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));


        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName("ADMIN").get();
        roles.add(role);
        user.setRoles(roles);


        userRepository.save(user);
        return "User registered successfully!";
    }
}
