package com.rana.library_management.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@Table(name="users")  //hibernate will create a table with the name of users
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO.IDENTITY)
    private Long id;
    private String userName;
    private String email;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)  //(1)it will create a separate table in the database with the name users_roles with two column id and roles
                                                  // (2) here fetch = FetchType.EAGER EAGER means the user roles is also loaded when i call UserRepo.findById(id)----> it will give user with their toles. but if i put her LAZY insted of EAGER then if i try to get user roles by calling userRepo.findById(id) then user.getRoles() it will return LAZYIntitilization ERROR
    private Set<String> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return userName;
    }



}
