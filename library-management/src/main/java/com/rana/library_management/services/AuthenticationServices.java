package com.rana.library_management.services;

import com.rana.library_management.DTO.LoginRequestDTO;
import com.rana.library_management.DTO.LoginResponseDTO;
import com.rana.library_management.DTO.RegisterRequestDTO;
import com.rana.library_management.entity.User;
import com.rana.library_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    public User registerNormalUser(RegisterRequestDTO registerRequestDTO){

        if(userRepository.findByUserName(registerRequestDTO.getUsername()).isPresent()){
            throw new RuntimeException("User Already Registerd.");
        }

        Set<String> roles = new HashSet<String>();
        roles.add("ROLE_USER");

        User user = new User();
        user.setUserName(registerRequestDTO.getUsername());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setRoles(roles);

        return userRepository.save(user);

    }


    public User registerAdminUser(RegisterRequestDTO registerRequestDTO){
        if(userRepository.findByUserName(registerRequestDTO.getUsername()).isPresent()){
            throw new RuntimeException("Admin Already registerd with this email .");
        }

        Set<String >roles = new HashSet<String>();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_USER");

        User user = new User();
        user.setUserName(registerRequestDTO.getUsername());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setRoles(roles);


        return userRepository.save(user);
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword())
        );

        User user = userRepository.findByUserName(loginRequestDTO.getUsername()).orElseThrow(()-> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user){

            return LoginResponseDTO.builder()
                    .token(token)
                    .username(user.getUserName())
                    .roles(user.getRoles())
                    .build();
        }
    }












}
