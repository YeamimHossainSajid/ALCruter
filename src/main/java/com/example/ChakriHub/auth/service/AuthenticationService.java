package com.example.ChakriHub.auth.service;

import com.example.ChakriHub.auth.CustomUserDetails;
import com.example.ChakriHub.auth.dto.request.LoginRequestDTO;
import com.example.ChakriHub.auth.dto.response.LoginResponseDTO;
import com.example.ChakriHub.config.security.jwt.JWTService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class AuthenticationService implements IAuthenticationService {


    private  AuthenticationManager authenticationManager;
    private JWTService jwtService;

    public AuthenticationService(AuthenticationManager authenticationManager, JWTService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO requestDTO ) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDTO.username(),
                        requestDTO.password()
                )
        );

        HashMap< String, Object > claims = new HashMap<>();

        CustomUserDetails user = ( CustomUserDetails ) authentication.getPrincipal();

        claims.put( "username", user.getUsername() );
        claims.put( "authorities", user.getAuthorities() );

        String jwtToken = jwtService.generateToken( claims, user );

        return new LoginResponseDTO(
                jwtToken,
                user.getUsername(),
                user.getEmail(),
                user
                        .getAuthorities()
                        .stream()
                        .map( GrantedAuthority::getAuthority )
                        .collect( Collectors.toSet() )
        );
    }
}