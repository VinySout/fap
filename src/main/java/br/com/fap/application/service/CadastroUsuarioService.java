package br.com.fap.application.service;

import java.net.URI;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fap.adapter.in.web.dto.SignUpRequest;
import br.com.fap.domain.exception.AppException;
import br.com.fap.domain.model.Role;
import br.com.fap.domain.model.RoleName;
import br.com.fap.domain.model.UsuarioModel;
import br.com.fap.domain.port.in.ICadastroUsuarioUseCase;
import br.com.fap.domain.port.out.RoleRepository;
import br.com.fap.domain.port.out.UsuarioRepository;

@Service
public class CadastroUsuarioService implements ICadastroUsuarioUseCase {
    
    @Autowired
    UsuarioRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

	@Override
	public URI cadastrarUsuario(SignUpRequest signUpRequest) {
    	
        // Creating user's account
        UsuarioModel user = new UsuarioModel(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        UsuarioModel result = userRepository.save(user);

        return ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();
	}
	
	public boolean validarUsuarioExistente(String username) {
		return userRepository.existsByUsername(username);
	}
	
	public boolean validarEmailExistente(String email) {
		return userRepository.existsByEmail(email);
	}

}
