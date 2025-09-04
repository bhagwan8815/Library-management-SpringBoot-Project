package com.rana.library_management.controller;

import com.rana.library_management.DTO.LoginRequestDTO;
import com.rana.library_management.DTO.LoginResponseDTO;
import com.rana.library_management.DTO.RegisterRequestDTO;
import com.rana.library_management.entity.User;
import com.rana.library_management.services.AuthenticationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

@Autowired
private AuthenticationServices authenticationServices;

   @PostMapping("/registernormaluser")
   public ResponseEntity<User> registerNormalUser(@RequestBody RegisterRequestDTO registerRequestDTO){
      return ResponseEntity.ok(authenticationServices.registerNormalUser(registerRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
       return ResponseEntity.ok(authenticationServices.login(loginRequestDTO));
    }


}
