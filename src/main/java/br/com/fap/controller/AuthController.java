package br.com.fap.controller;

import java.net.URI;
import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fap.config.security.JwtTokenProvider;
import br.com.fap.exception.AppException;
import br.com.fap.model.Role;
import br.com.fap.model.RoleName;
import br.com.fap.model.UsuarioModel;
import br.com.fap.payload.ApiResponse;
import br.com.fap.payload.JwtAuthenticationResponse;
import br.com.fap.payload.LoginRequest;
import br.com.fap.payload.SignUpRequest;
import br.com.fap.repository.RoleRepository;
import br.com.fap.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    UsuarioRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;
    
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
    	
		if(userRepository.existsByUsername(signUpRequest.getUsername())) {   
			String nomeExistente = signUpRequest.getUsername();
            return new ResponseEntity(new ApiResponse(false, 
            		"Nome para Login: '" + nomeExistente + "' já cadastrado em nossa base! (Escolha outro)."),
                    HttpStatus.BAD_REQUEST);                
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
        	String emailExistente = signUpRequest.getEmail();
            return new ResponseEntity(new ApiResponse(false, 
            		"Email: '" + emailExistente + "' já cadastrado em base! (Escolha outro)."),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        UsuarioModel user = new UsuarioModel(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        UsuarioModel result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }

}
